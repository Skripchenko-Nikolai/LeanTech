package com.pirksni.leantech.presentation.screen.personeat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentPersonEatBinding
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.setThrottledClickListener
import com.pirksni.leantech.extensions.textChangeListener
import com.pirksni.leantech.presentation.base.BaseFragment
import com.pirksni.leantech.presentation.customview.ItemPersonEat
import kotlinx.coroutines.flow.onEach

class PersonEatFragment : BaseFragment<PersonEatViewModel>(R.layout.fragment_person_eat) {

    private val binding by viewBinding(FragmentPersonEatBinding::bind)

    private val args by navArgs<PersonEatFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
//            scrollView.updateTopPaddingEdgeToEdge()
            btnSave.setThrottledClickListener {
                viewModel.onEvent(PersonEatState.Event.OnSaveEat(args.positionPerson))
            }
            tiCount.textChangeListener {
                onEatChange(tvComment.text.toString(), it)
            }
        }
        with(viewModel) {
            onEvent(PersonEatState.Event.OnGetPersonEatSpreadSheet)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
            stateFlow.onEach(::createView).launchWhenStarted(lifecycleScope)
        }
    }

    private fun createView(text: List<String>) {
        with(binding) {
            text.forEach {
                if (it != COMMENTS) {
                    val item = ItemPersonEat(requireContext())
                    item.setText(it)
                    item.onCountChangeListener(::onEatChange)
                    eatContainer.addView(item)
                } else {
                    // ignore
                }
            }
            llComment.isVisible = true
            btnSave.isVisible = true
        }
    }

    private fun handleUiLabel(uiLabel: PersonEatState.UiLabel) {
        when (uiLabel) {
            PersonEatState.UiLabel.HideProgressBar ->
                hideProgressBar()
            PersonEatState.UiLabel.ShowProgressBar ->
                showProgressBar()
            PersonEatState.UiLabel.None -> {
                // ignore
            }
            PersonEatState.UiLabel.ExitScreen ->
                findNavController().navigateUp()
        }
    }

    private fun onEatChange(eat: String, count: String) {
        viewModel.onEvent(PersonEatState.Event.OnEatChange(eat, count))
    }

    private fun showProgressBar() {
        binding.flProgress.isVisible = true
    }

    private fun hideProgressBar() {
        binding.flProgress.isVisible = false
    }

    companion object {
        private const val COMMENTS = "комментарии"
    }
}
