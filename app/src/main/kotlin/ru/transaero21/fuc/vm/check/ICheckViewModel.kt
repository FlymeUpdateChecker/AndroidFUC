package ru.transaero21.fuc.vm.check

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.enums.RequestStatus
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.entity.state.FirmwareState

interface ICheckViewModel {
    val status: StateFlow<RequestStatus>
    val firmware: StateFlow<FirmwareState>

    fun requestCheck(deviceData: DeviceData)
    fun cancelCheck()
}