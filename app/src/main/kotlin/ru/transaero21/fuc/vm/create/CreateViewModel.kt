package ru.transaero21.fuc.vm.create

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.transaero21.fuc.data.repo.devices.IDevicesRepository
import ru.transaero21.fuc.entity.state.DeviceState
import ru.transaero21.fuc.entity.state.FieldState
import javax.inject.Inject
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties


@HiltViewModel
class CreateViewModel @Inject constructor(
    private val devicesRepo: IDevicesRepository
) : ViewModel(), ICreateViewModel {
    private val _device = MutableStateFlow(DeviceState())
    override val device = _device.asStateFlow()

    init {
        resetDefaults()
    }

    private fun setTitleValue(title: String) {
        _device.update { it.copy(title = it.title.copy(value = title, isError = false)) }
    }

    private fun setAndroidValue(android: String) {
        _device.update { it.copy(android = it.android.copy(value = android, isError = false)) }
    }

    private fun setCodenameValue(codename: String) {
        _device.update { it.copy(codename = it.codename.copy(value = codename, isError = false)) }
    }

    private fun setSnValue(sn: String) {
        _device.update { it.copy(sn = it.sn.copy(value = sn, isError = false)) }
    }

    private fun setImeiValue(imei: String) {
        _device.update { it.copy(imei = it.imei.copy(value = imei, isError = false)) }
    }

    private fun setTypeValue(type: String) {
        _device.update { it.copy(type = it.type.copy(value = type, isError = false)) }
    }

    private fun setTimestampValue(timestamp: String) {
        timestamp.trim().let { old ->
            (if (old.isEmpty() || old.toLongOrNull() != null) old else "").let { new ->
                _device.update { it.copy(timestamp = it.timestamp.copy(value = new, isError = false)) }
            }
        }
    }

    private fun setHostValue(host: String) {
        _device.update { it.copy(host = it.host.copy(value = host, isError = false)) }
    }

    override fun verifyPrimary(): Boolean {
        return _device.value.verifyParams { new -> _device.update { new } }
    }

    override fun saveDevice(): Boolean {
        _device.value.title.value.trim().let { title ->
           if (title.isEmpty() || devicesRepo.isNameRepeated(title)) {
               _device.update { it.copy(title = it.title.copy(isError = true)) }
               return false
           }
        }
        devicesRepo.addDevice(_device.value.toData(null))
        viewModelScope.launch {
            delay(250)
            resetDefaults()
        }
        return true
    }

    override fun syncData() {
        try {
            val mask = getProperty("ro.build.mask.id")
            mask.split("-").let { sp1 ->
                setAndroidValue(sp1[0])
                sp1[1].split("_").let { sp2 ->
                    setTimestampValue(sp2[0])
                    setTypeValue(sp2[1])
                }
            }
            setCodenameValue(getProperty("ro.vendor.meizu.product.model"))
        } catch (_: Exception) { }
    }

    @SuppressLint("PrivateApi")
    private fun getProperty(key: String): String {
        return Class.forName("android.os.SystemProperties")
            .getMethod("get", String::class.java).invoke(null, key) as String
    }

    override fun resetDefaults() {
        _device.value = DeviceState(
            setTitleValue = this::setTitleValue,
            setCodenameValue = this::setCodenameValue,
            setSnValue = this::setSnValue,
            setImeiValue = this::setImeiValue,
            setAndroidValue = this::setAndroidValue,
            setTypeValue = this::setTypeValue,
            setTimestampValue = this::setTimestampValue,
            setHostValue = this::setHostValue,
        )
    }
}