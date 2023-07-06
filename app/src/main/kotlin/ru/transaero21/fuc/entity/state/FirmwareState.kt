package ru.transaero21.fuc.entity.state

import org.jsoup.Jsoup
import ru.transaero21.fuc.entity.dto.info.firmware.FirmwareInfo

data class FirmwareState(
    val version: String = "",
    val releaseTime: String = "",
    val android: String = "",
    val packageSize: String = "",
    val changeLog: String = "",
    val packageUrl: String = ""
) {
    constructor(info: FirmwareInfo) : this(
        version = "${info.systemName} ${info.systemVersion}",
        releaseTime = info.releaseDate ?: "",
        android = info.latestVersion?.split("-")?.get(0) ?: "",
        packageSize = info.fileSize ?: "",
        changeLog = buildChangeLog(info.releaseNoteStyle ?: "", info.releaseNote ?: ""),
        packageUrl = info.updateUrl ?: ""
    )
}

fun buildChangeLog(releaseNoteStyle: String, releaseNote: String): String {
    val html = "<html><head>$releaseNoteStyle</head><body>$releaseNote</body></html>"
    val doc = Jsoup.parse(html)
    for (el in doc.body().children().select("*")) {
        el.removeAttr("style")
    }
    return doc.html()
}
