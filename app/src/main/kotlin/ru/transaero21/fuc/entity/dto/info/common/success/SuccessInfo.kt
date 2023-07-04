package ru.transaero21.fuc.entity.dto.info.common.success

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.info.common.MzInfo

@Serializable
data class SuccessInfo(
    @SerialName("reply") val reply: SuccessWrapperInfo
) : MzInfo()


