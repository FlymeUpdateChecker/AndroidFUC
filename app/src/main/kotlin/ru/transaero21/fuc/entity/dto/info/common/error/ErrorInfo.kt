package ru.transaero21.fuc.entity.dto.info.common.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.info.common.BasicInfo

@Serializable
data class ErrorInfo(
    @SerialName("code") override val code: String,
    @SerialName("value") override val value: String,
    @SerialName("message") override val message: String,
    @SerialName("redirect") val redirect: String
): BasicInfo<String>()
