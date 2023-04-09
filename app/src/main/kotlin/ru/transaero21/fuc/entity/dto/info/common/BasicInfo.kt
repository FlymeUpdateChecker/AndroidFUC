package ru.transaero21.fuc.entity.dto.info.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class BasicInfo<T> : MzInfo() {
    @SerialName("code") abstract val code: String
    @SerialName("message") abstract val message: String
    @SerialName("value") abstract val value: T
}
