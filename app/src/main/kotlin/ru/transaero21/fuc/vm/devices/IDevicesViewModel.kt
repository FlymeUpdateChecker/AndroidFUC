package ru.transaero21.fuc.vm.devices

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.entity.state.SmallDeviceState

interface IDevicesViewModel {
    val devicesList: StateFlow<List<SmallDeviceState>>
    val renameField: StateFlow<FieldState>

    fun deleteDevice(id: Int)
    fun setTitle(id: Int): Boolean
}