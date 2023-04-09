package ru.transaero21.fuc.data.repo.devices

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.transaero21.fuc.data.db.dao.DeviceDataDao
import ru.transaero21.fuc.entity.model.DeviceData
import javax.inject.Inject

class DevicesRepository @Inject constructor(
    private val deviceDataDao: DeviceDataDao
): IDevicesRepository {

    private val _devicesList = MutableStateFlow(emptyList<DeviceData>())
    override val devicesList = _devicesList.asStateFlow()

    init {
        _devicesList.value = deviceDataDao.getAll()
    }

    override fun addDevice() {
        TODO("Not yet implemented")
    }

    override fun removeDevice(id: Int) {
        _devicesList.value.firstOrNull { it.id == id }?.let { deviceData ->
            deviceDataDao.delete(deviceData)
        }
    }
}