package ru.transaero21.fuc.vm.check

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.transaero21.fuc.data.repo.check.ICheckRepository
import ru.transaero21.fuc.data.repo.settings.ISettingsRepository
import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.entity.enums.RequestStatus
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.entity.state.FirmwareState
import javax.inject.Inject

private const val TAG = "CheckViewModel"

@HiltViewModel
class CheckViewModel @Inject constructor(
    private val checkRepo: ICheckRepository,
    private val settingsRepo: ISettingsRepository
) : ViewModel(), ICheckViewModel {
    private val _status = MutableStateFlow(RequestStatus.LOADING)
    override val status = _status.asStateFlow()
    private val _firmware = MutableStateFlow(FirmwareState())
    override val firmware = _firmware.asStateFlow()

    private val isV2 = MutableStateFlow(true)
    init {
        viewModelScope.launch {
            settingsRepo.isV2Enabled.collect { isV2.value = it }
        }
    }

    override fun requestCheck(deviceData: DeviceData) {
        setStatus(RequestStatus.LOADING)
        viewModelScope.launch {
            checkRepo.checkSys(deviceData, isV2.value, ::callback)
        }
    }

    override fun cancelCheck() {
        viewModelScope.launch { checkRepo.cancelCheck() }
    }

    private fun callback(info: SuccessInfo?, status: RequestStatus) {
        if (status == RequestStatus.FOUND_NEW) {
            info?.reply?.value?.new?.let { new ->
                _firmware.value = FirmwareState(new)
            }
        } else if (status == RequestStatus.NOTHING_NEW) {
            info?.reply?.value?.cur?.let { cur ->
                _firmware.value = FirmwareState(cur)
            } ?: _firmware.update { _firmware.value.copy(changeLog = "") }
        }
        setStatus(status)
    }

    private fun setStatus(status: RequestStatus) {
        Log.d(TAG, "setStatus: called with: status=$status.")
        _status.update { status }
    }
}