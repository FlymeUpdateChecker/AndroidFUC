package ru.transaero21.fuc.vm.device

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.transaero21.fuc.data.repo.devices.IDevicesRepository
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.entity.state.DeviceState
import ru.transaero21.fuc.vm.check.CheckViewModel
import ru.transaero21.fuc.vm.check.ICheckViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val devicesRepo: IDevicesRepository
) : ViewModel(), IDeviceViewModel {
    private var original: DeviceData? = null
    private val _device = MutableStateFlow(DeviceState())
    override val device = _device.asStateFlow()
    private val _isChanged = MutableStateFlow(false)
    override val isChanged = _isChanged.asStateFlow()

    private fun setTitleValue(title: String) {
        _device.update { it.copy(title = it.title.copy(value = title, isError = false)) }
        compareOriginal()
    }

    private fun setAndroidValue(android: String) {
        _device.update { it.copy(android = it.android.copy(value = android, isError = false)) }
        compareOriginal()
    }

    private fun setCodenameValue(codename: String) {
        _device.update { it.copy(codename = it.codename.copy(value = codename, isError = false)) }
        compareOriginal()
    }

    private fun setSnValue(sn: String) {
        _device.update { it.copy(sn = it.sn.copy(value = sn, isError = false)) }
        compareOriginal()
    }

    private fun setImeiValue(imei: String) {
        _device.update { it.copy(imei = it.imei.copy(value = imei, isError = false)) }
        compareOriginal()
    }

    private fun setTypeValue(type: String) {
        _device.update { it.copy(type = it.type.copy(value = type, isError = false)) }
        compareOriginal()
    }

    private fun setTimestampValue(timestamp: String) {
        timestamp.trim().let { old ->
            (if (old.isEmpty() || old.toLongOrNull() != null) old else "").let { new ->
                _device.update { it.copy(timestamp = it.timestamp.copy(value = new, isError = false)) }
            }
        }
        compareOriginal()
    }

    private fun setHostValue(host: String) {
        _device.update { it.copy(host = it.host.copy(value = host, isError = false)) }
        compareOriginal()
    }

    private fun compareOriginal() {
        _isChanged.value = original?.let {
            _device.value.codename.value != original?.codename ||
                    _device.value.sn.value != original?.sn ||
                    _device.value.imei.value != original?.imei ||
                    _device.value.android.value != original?.android ||
                    _device.value.type.value != original?.type ||
                    _device.value.timestamp.value != original?.timestamp ||
                    _device.value.host.value != original?.host
        } ?: false
    }

    override fun verifyPrimary(): Boolean {
        return _device.value.verifyParams { new -> _device.update { new } }
    }

    override fun saveDevice(): Boolean {
        if (!isChanged.value || !verifyPrimary()) return false
        _device.value.toData(original?.id).let { data ->
            devicesRepo.updateDevice(data)
            _isChanged.value = false
            original = data
        }
        return true
    }

    override fun restoreOriginal() = setDevice(original?.id ?: -1)

    override fun setDevice(id: Int) {
        _isChanged.value = false
        original = devicesRepo.getDevice(id)
        original?.let { device -> _device.update { DeviceState(
            device = device,
            setTitleValue = this::setTitleValue,
            setCodenameValue = this::setCodenameValue,
            setSnValue = this::setSnValue,
            setImeiValue = this::setImeiValue,
            setAndroidValue = this::setAndroidValue,
            setTypeValue = this::setTypeValue,
            setTimestampValue = this::setTimestampValue,
            setHostValue = this::setHostValue,
        )}}
    }
}
