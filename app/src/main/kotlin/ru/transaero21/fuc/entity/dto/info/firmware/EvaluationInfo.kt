package ru.transaero21.fuc.entity.dto.info.firmware

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvaluationInfo(
    @SerialName("evalProjectId") val evalProjectId: Int?,
    @SerialName("averageScore") val averageScore: Int?,
    @SerialName("existEvalProject") val existEvalProject: Boolean?,
    @SerialName("totalCounts") val totalCounts: Int?,
    @SerialName("showEvalCounts") val showEvalCounts: Int?
)
