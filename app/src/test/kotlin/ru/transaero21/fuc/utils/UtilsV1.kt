package ru.transaero21.fuc.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.transaero21.fuc.entity.dto.input.v1.FirmwareV1Input
import ru.transaero21.fuc.entity.dto.input.v1.SysV1Input

object UtilsV1 {
    val firmware = FirmwareV1Input(
        deviceType = "M8820",
        firmware = "10",
        root = "0",
        sysVer = "10-1577826000_stable",
        version = "10-1577826000_stable",
        imei = "123456789012345",
        sn = "1234567890ABC"
    )

    val sys = SysV1Input(
        sys = Json.encodeToString(firmware)
    )
}