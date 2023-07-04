package ru.transaero21.fuc.entity.state

import ru.transaero21.fuc.entity.model.DeviceData
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

data class DeviceState(
    val id: Int? = null,
    val title: FieldState,
    val codename: FieldState,
    val imei: FieldState,
    val sn: FieldState,
    val android: FieldState,
    val type: FieldState,
    val timestamp: FieldState,
    val host: FieldState
) {
    constructor(
        device: DeviceData = DeviceData(),
        setTitleValue: (String) -> Unit = {},
        setCodenameValue: (String) -> Unit = {},
        setSnValue: (String) -> Unit = {},
        setImeiValue: (String) -> Unit = {},
        setAndroidValue: (String) -> Unit = {},
        setTypeValue: (String) -> Unit = {},
        setTimestampValue: (String) -> Unit = {},
        setHostValue: (String) -> Unit = {},
    ) : this(
        title = FieldState(value = device.title, setValue = setTitleValue),
        codename = FieldState(value = device.codename, setValue = setCodenameValue),
        imei = FieldState(value = device.imei, setValue = setImeiValue),
        sn = FieldState(value = device.sn, setValue = setSnValue),
        android = FieldState(value = device.android, setValue = setAndroidValue),
        type = FieldState(value = device.type, setValue = setTypeValue),
        timestamp = FieldState(value = device.timestamp, setValue = setTimestampValue),
        host = FieldState(value = device.host, setValue = setHostValue),
    )

    fun toData(id: Int?) = DeviceData(
        id = id,
        title = this.title.value.trim(),
        codename = this.codename.value,
        imei = this.imei.value,
        sn = this.sn.value,
        android = this.android.value,
        type = this.type.value,
        timestamp = this.timestamp.value,
        host = this.host.value
    )

    fun verifyParams(callback: (DeviceState) -> Unit): Boolean {
        var isOk = true
        var deviceData = this
        deviceData::class.memberProperties.let { member ->
            val mCopy = this::class.memberFunctions.first { it.name == "copy" }
            member.forEach { m ->
                val mParam = mCopy.parameters.first { it.name == m.name }
                (m.getter.call(deviceData) as FieldState?)?.let { field ->
                    if (field.value.isBlank() && m.name != "title") {
                        val copy = field::class.memberFunctions.first { it.name == "copy" }
                        val param = copy.parameters.first { it.name == "isError" }
                        deviceData = mCopy.callBy(mapOf(
                            mCopy.instanceParameter!! to deviceData,
                            mParam to copy.callBy(
                                mapOf(copy.instanceParameter!! to field, param to true)
                            )
                        )) as DeviceState
                        isOk = false
                    }
                }
            }
        }
        callback(deviceData)
        return isOk
    }
}