package ru.transaero21.fuc.entity.dto.input.common

import kotlinx.serialization.Serializable

@Serializable
abstract class FirmwareInput {
    abstract val deviceType: String
    abstract val firmware: String
    abstract val root: String
    abstract val sysVer: String
    abstract val version: String
    abstract val sn: String
}
