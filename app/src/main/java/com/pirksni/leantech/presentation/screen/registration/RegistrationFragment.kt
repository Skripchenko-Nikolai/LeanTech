package com.pirksni.leantech.presentation.screen.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentRegistrationBinding
import com.pirksni.leantech.extensions.updateBottomPaddingEdgeToEdge
import com.pirksni.leantech.extensions.updateTopPaddingEdgeToEdge
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.extensions.setThrottledClickListener
import com.pirksni.leantech.presentation.base.BaseFragment
import kotlinx.coroutines.flow.onEach

class RegistrationFragment : BaseFragment<RegistrationViewModel>(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignINResult(task)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            clRegistration.updateTopPaddingEdgeToEdge()
            flButtonAuth.updateBottomPaddingEdgeToEdge()

            val client = createGoogleSignInClient()

            if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
                viewModel.onEvent(RegistrationState.Event.OnStartNextScreen)
            } else {
                btnRegister.setThrottledClickListener {
                    resultLauncher.launch(client.signInIntent)
                }
            }
        }
        viewModel.uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
    }

    private fun createGoogleSignInClient(): GoogleSignInClient {
        val googleSignOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestEmail()
            .build()
        return GoogleSignIn.getClient(requireContext(), googleSignOptions)
    }

    private fun handleSignINResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // TODO implemented надо будет сохраннять email
            viewModel.onEvent(RegistrationState.Event.OnStartNextScreen)
        } catch (e: ApiException) {
            handleSignInError(e)
        }
    }

    private fun handleSignInError(exception: ApiException) {
        when (exception.statusCode) {
            SIGN_IN_CANCELED_CODE_ERROR ->
                showSnackbars(R.string.sign_in_canceled_error)
            SIGN_IN_PROGRESS_CODE_ERROR ->
                showSnackbars(R.string.sign_in_progress_error)
            SIGN_IN_FAILED_CODE_ERROR ->
                showSnackbars(R.string.sign_in_failed_error)
            else ->
                showSnackbars(R.string.unknown_error)
        }
    }

    private fun handleUiLabel(uiLabel: RegistrationState.UiLabel) {
        when (uiLabel) {
            RegistrationState.UiLabel.None -> {
                // ignore
            }
            RegistrationState.UiLabel.OnStartFilledProfileScreen ->
                findNavController().navigate(R.id.registration_fragment_to_filled_profile_fragment)
            RegistrationState.UiLabel.OnStartMenuScreen -> {
                findNavController().navigate(R.id.registration_fragment_to_menu_fragment)
            }
        }
    }

    companion object {
        private const val SIGN_IN_CANCELED_CODE_ERROR = 12501
        private const val SIGN_IN_PROGRESS_CODE_ERROR = 12502
        private const val SIGN_IN_FAILED_CODE_ERROR = 12500
    }
}
