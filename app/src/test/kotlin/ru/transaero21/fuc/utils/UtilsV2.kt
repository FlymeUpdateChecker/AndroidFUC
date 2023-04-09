package ru.transaero21.fuc.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.transaero21.fuc.entity.dto.input.v2.FirmwareV2Input
import ru.transaero21.fuc.entity.dto.input.v2.SysV2Input

object UtilsV2 {
    val firmware = FirmwareV2Input(
        deviceType = "M8820",
        firmware = "8.1.0",
        root = "0",
        sysVer = "8.1.0-1564036703_stable",
        version = "8.1.0-1564036703_stable",
        deviceId = "123456789012345",
        sn = "1234567890ABC",
    )

    val sys = SysV2Input(
        sys = Json.encodeToString(firmware)
    )
}