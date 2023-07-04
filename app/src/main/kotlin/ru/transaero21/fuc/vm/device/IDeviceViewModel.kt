package ru.transaero21.fuc.vm.device

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.state.DeviceState

interface IDeviceViewModel {
    val device: StateFlow<DeviceState>
    val isChanged: StateFlow<Boolean>

    fun verifyPrimary(): Boolean
    fun saveDevice(): Boolean
    fun restoreOriginal()
    fun setDevice(id: Int)
}