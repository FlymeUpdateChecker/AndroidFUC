package ru.transaero21.fuc.entity.dto.info.common.success

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.info.common.BasicInfo
import ru.transaero21.fuc.entity.dto.info.firmware.FirmwareArray

@Serializable
data class SuccessWrapperInfo(
    @SerialName("code") override val code: String,
    @SerialName("value") override val value: FirmwareArray,
    @SerialName("message") override val message: String,
): BasicInfo<FirmwareArray>()
