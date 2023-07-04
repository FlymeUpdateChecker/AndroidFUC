package ru.transaero21.fuc.vm.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.transaero21.fuc.data.repo.devices.IDevicesRepository
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.entity.state.SmallDeviceState
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(
    private val devicesRepo: IDevicesRepository
): ViewModel(), IDevicesViewModel {
    private val _devicesList = MutableStateFlow(emptyList<SmallDeviceState>())
    override val devicesList = _devicesList.asStateFlow()
    private val _renameField = MutableStateFlow(FieldState(value = "", setValue = ::presetTitle))
    override val renameField = _renameField.asStateFlow()

    init {
        viewModelScope.launch {
            devicesRepo.devicesList.collect { list ->
                _devicesList.value = list.map { SmallDeviceState(it) }.reversed()
            }
        }
    }

    private fun presetTitle(title: String) {
        _renameField.update { it.copy(value = title) }
    }

    override fun deleteDevice(id: Int) {
        devicesRepo.removeDevice(id)
    }

    override fun setTitle(id: Int): Boolean {
        var isOk = true
        val title = _renameField.value.value
        if (title.isBlank() || devicesRepo.isNameRepeated(title.trim())) isOk = false
        devicesRepo.getDevice(id)?.let { device ->
            devicesRepo.updateDevice(device.copy(title = title))
        } ?: run { isOk = false }
        _renameField.update { it.copy(isError = !isOk) }
        return isOk
    }
}