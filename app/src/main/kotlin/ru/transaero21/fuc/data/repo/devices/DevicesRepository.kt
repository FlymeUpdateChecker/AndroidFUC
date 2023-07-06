package ru.transaero21.fuc.data.repo.devices

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.transaero21.fuc.data.db.dao.DeviceDataDao
import ru.transaero21.fuc.entity.model.DeviceData
import javax.inject.Inject

private const val TAG = "DevicesRepository"

class DevicesRepository @Inject constructor(
    private val deviceDataDao: DeviceDataDao
): IDevicesRepository {
    private val _devicesList = MutableStateFlow(emptyList<DeviceData>())
    override val devicesList = _devicesList.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        Log.d(TAG, "Initialize")
        scope.launch {
            deviceDataDao.getAll().collect { _devicesList.emit(it) }
        }
    }

    override fun getDevice(id: Int): DeviceData? {
        return runBlocking(scope.coroutineContext) {
            _devicesList.value.firstOrNull { it.id == id } ?: deviceDataDao.getById(id)
        }
    }

    override fun addDevice(data: DeviceData) {
        runBlocking(scope.coroutineContext) {
            if (data.id == null) deviceDataDao.insertAll(data)
        }
    }

    override fun updateDevice(data: DeviceData) {
        runBlocking(scope.coroutineContext) {
            if (data.id != null) deviceDataDao.updateAll(data)
        }
    }

    override fun removeDevice(id: Int) {
        runBlocking(scope.coroutineContext) {
            _devicesList.value.firstOrNull { it.id == id }?.let { deviceData ->
                deviceDataDao.delete(deviceData)
            }
        }
    }

    override fun isNameRepeated(name: String): Boolean {
        return _devicesList.value.firstOrNull { it.title == name } != null
    }
}