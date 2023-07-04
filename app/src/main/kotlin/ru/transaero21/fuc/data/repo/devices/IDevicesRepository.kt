package ru.transaero21.fuc.data.repo.devices

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.model.DeviceData

interface IDevicesRepository {
    val devicesList: StateFlow<List<DeviceData>>

    fun getDevice(id: Int): DeviceData?
    fun addDevice(data: DeviceData)
    fun updateDevice(data: DeviceData)
    fun removeDevice(id: Int)
    fun isNameRepeated(name: String): Boolean
}