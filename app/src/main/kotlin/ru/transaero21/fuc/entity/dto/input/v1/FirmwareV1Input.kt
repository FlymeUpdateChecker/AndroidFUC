package ru.transaero21.fuc.entity.dto.input.v1

import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.input.common.FirmwareInput

@Serializable
data class FirmwareV1Input(
    override val deviceType: String,
    override val firmware: String,
    override val root: String,
    override val sysVer: String,
    override val version: String,
    override val sn: String,
    val imei: String
) : FirmwareInput()
