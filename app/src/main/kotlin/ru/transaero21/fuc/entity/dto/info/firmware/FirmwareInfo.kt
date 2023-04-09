package ru.transaero21.fuc.entity.dto.info.firmware

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirmwareInfo(
    @SerialName("appid") val appId: Int?,
    @SerialName("existsUpdate") val existsUpdate: Boolean?,
    @SerialName("needUpdate") val needUpdate: Boolean?,
    @SerialName("optionalClear") val optionalClear: Int?,
    @SerialName("mandatoryClear") val mandatoryClear: Boolean?,
    @SerialName("latestVersion") val latestVersion: String?,
    @SerialName("systemName") val systemName: String?,
    @SerialName("systemVersion") val systemVersion: String?,
    @SerialName("releaseDate") val releaseDate: String?,
    @SerialName("releaseNote") val releaseNote: String?,
    @SerialName("releaseNoteStyle") val releaseNoteStyle: String?,
    @SerialName("updateUrl") val updateUrl: String?,
    @SerialName("fileSize") val fileSize: String?,
    @SerialName("packageType") val packageType: Int?,
    @SerialName("pictures") val pictures: String?,
    @SerialName("videos") val videos: String?,
    @SerialName("brightSpots") val brightSpots: String?,
    @SerialName("tips") val tips: Int?,
    @SerialName("installation") val installation: Int?,
    @SerialName("appraise") val appraise: Int?,
    @SerialName("likeCount") val likeCount: Int?,
    @SerialName("disLikeCount") val disLikeCount: Int?,
    @SerialName("verType") val verType: String?,
    @SerialName("testDevices") val testDevices: String?,
    @SerialName("size") val size: Int?,
    @SerialName("digest") val digest: String?,
    @SerialName("verifyMode") val verifyMode: Int?,
    @SerialName("notifyBgUrl") val notifyBgUrl: String?,
    @SerialName("mandatoryUpgrade") val mandatoryUpgrade: String?,
    @SerialName("mandatoryTitle") val mandatoryTitle: String?,
    @SerialName("content") val content: String?,
    @SerialName("introduction") val introduction: String?,
    @SerialName("dynamicNotifySwitch") val dynamicNotifySwitch: Int?,
    @SerialName("brightSpotsArray") val brightSpotsArray: String?,
    @SerialName("advancedOptions") val advancedOptions: Int?,
    @SerialName("downloadCondition") val downloadCondition: Int?,
    @SerialName("upgradeCondition") val upgradeCondition: Int?,
    @SerialName("clearNotify") val clearNotify: Int?,
    @SerialName("evaluation") val evaluation: EvaluationInfo?,
    @SerialName("silentDownload") val silentDownload: Int?,
    @SerialName("protocolDetail") val protocolDetail: String?,
    @SerialName("seeDetail") val seeDetail: Int?,
)