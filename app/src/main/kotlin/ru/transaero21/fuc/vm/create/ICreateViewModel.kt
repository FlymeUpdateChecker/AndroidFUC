package ru.transaero21.fuc.vm.create

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.state.DeviceState

interface ICreateViewModel {
    val device: StateFlow<DeviceState>

    fun verifyPrimary(): Boolean
    fun saveDevice(): Boolean
    fun syncData()
    fun resetDefaults()
}