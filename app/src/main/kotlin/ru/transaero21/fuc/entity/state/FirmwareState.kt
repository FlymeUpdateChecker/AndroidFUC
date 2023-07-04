package ru.transaero21.fuc.entity.state

import ru.transaero21.fuc.entity.dto.info.firmware.FirmwareInfo

data class FirmwareState(
    val version: String = "",
    val releaseTime: String = "",
    val android: String = "",
    val packageSize: String = "",
    val changeLog: String = "",
    val changeLogStyle: String = "",
    val packageUrl: String = ""
) {
    constructor(info: FirmwareInfo) : this(
        version = "${info.systemName} ${info.systemVersion}",
        releaseTime = info.releaseDate ?: "",
        android = info.latestVersion?.split("-")?.get(0) ?: "",
        packageSize = info.fileSize ?: "",
        changeLog = info.releaseNote ?: "",
        changeLogStyle = info.releaseNoteStyle ?: "",
        packageUrl = info.updateUrl ?: ""
    )
}
