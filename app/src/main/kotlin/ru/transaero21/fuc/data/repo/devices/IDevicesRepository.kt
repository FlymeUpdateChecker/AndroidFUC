package ru.transaero21.fuc.data.repo.devices

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.model.DeviceData

interface IDevicesRepository {
    val devicesList: StateFlow<List<DeviceData>>

    fun addDevice()
    fun removeDevice(id: Int)
}