package ru.transaero21.fuc.entity.dto.info.firmware

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirmwareArray(
    @SerialName("new") val new: FirmwareInfo?,
    @SerialName("plan") val plan: FirmwareInfo?,
    @SerialName("cur") val cur: FirmwareInfo?,
)