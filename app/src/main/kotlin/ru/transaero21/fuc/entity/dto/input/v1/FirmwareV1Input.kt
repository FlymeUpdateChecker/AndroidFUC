package ru.transaero21.fuc.entity.dto.input.v1

import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.input.common.FirmwareInput
import ru.transaero21.fuc.entity.model.DeviceData

@Serializable
data class FirmwareV1Input(
    override val deviceType: String,
    override val firmware: String,
    override val root: String,
    override val sysVer: String,
    override val version: String,
    override val sn: String,
    val imei: String
) : FirmwareInput() {
    constructor(deviceData: DeviceData) : this(
        deviceType = deviceData.codename,
        firmware = deviceData.android,
        root = "0",
        sysVer = "${deviceData.android}-${deviceData.timestamp}_${deviceData.type}",
        version = "${deviceData.android}-${deviceData.timestamp}_${deviceData.type}",
        sn = deviceData.sn,
        imei = deviceData.imei
    )
}
