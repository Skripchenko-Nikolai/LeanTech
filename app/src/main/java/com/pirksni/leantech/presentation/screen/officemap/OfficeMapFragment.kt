package com.pirksni.leantech.presentation.screen.officemap

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentOfficeMapBinding
import com.pirksni.leantech.domain.model.PointModel
import com.pirksni.leantech.extensions.launchWhenStarted
import com.pirksni.leantech.extensions.showSnackbars
import com.pirksni.leantech.presentation.base.BaseFragment
import com.pirksni.leantech.presentation.util.alertDialogBuilder
import kotlinx.coroutines.flow.onEach

class OfficeMapFragment : BaseFragment<OfficeMapViewModel>(R.layout.fragment_office_map) {

    private val binding by viewBinding(FragmentOfficeMapBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            ivMap.setOnClickListener {
                saveCoordinate()
            }
        }
        with(viewModel) {
            viewModel.onEvent(OfficeMapState.Event.OnGetPoint)
            stateFlow.onEach(::handleUiState).launchWhenStarted(lifecycleScope)
            uiLabelFlow.onEach(::handleUiLabel).launchWhenStarted(lifecycleScope)
            pointFlow.onEach { points ->
                points.forEach { point ->
                    placeImage(point)
                }
            }.launchWhenStarted(lifecycleScope)
        }
    }

    private fun handleUiState(uiState: OfficeMapState.Model) {
        when (uiState.error) {
            OfficeMapState.Model.Error.ERROR_SAVE_POINT ->
                showSnackbars(R.string.error_save_point)
            OfficeMapState.Model.Error.ERROR_GET_POINT ->
                showSnackbars(R.string.error_get_point)
            OfficeMapState.Model.Error.NO_INFO_PERSON ->
                showSnackbars(R.string.error_get_profile_info)
            OfficeMapState.Model.Error.NO_COORDINATE_POINT ->
                showSnackbars(R.string.error_empty_coordinates)
            OfficeMapState.Model.Error.NONE -> {
                // ignore
            }
        }
    }

    private fun handleUiLabel(uiLabel: OfficeMapState.UiLabel) {
        when (uiLabel) {
            OfficeMapState.UiLabel.HideProgressBar ->
                binding.flProgress.isVisible = true
            OfficeMapState.UiLabel.ShowProgressBar ->
                binding.flProgress.isVisible = false
            is OfficeMapState.UiLabel.ShowSnackbarSetPoint ->
                showSnackbars(uiLabel.message)
            OfficeMapState.UiLabel.None -> {
                // ignore
            }
        }
    }

    private fun saveCoordinate() {
        val coordinate = binding.ivMap.coordinate
        coordinate?.let {
            viewModel.onEvent(
                OfficeMapState.Event.OnPointChange(
                    coordinate.first.toDouble(),
                    coordinate.second.toDouble()
                )
            )
            showUsbPrinterConnectDialog()
        }

    }

    private fun showUsbPrinterConnectDialog() {
        alertDialogBuilder(
            context = requireContext(),
            title = R.string.dialog_attention,
            message = R.string.dialog_message_save_point,
            negativeButtonName = R.string.dialog_back,
            positiveButtonName = R.string.dialog_yes,
            positiveButtonCallback = {
                viewModel.onEvent(OfficeMapState.Event.OnSetPoint)
                binding.ivMap.coordinate = null
            },
            negativeButtonCallback = {
                binding.ivMap.coordinate = null
            }
        ).show()
    }

    private fun placeImage(pointModel: PointModel) {

    }
}
