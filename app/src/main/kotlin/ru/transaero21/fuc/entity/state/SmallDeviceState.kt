package ru.transaero21.fuc.entity.state

import ru.transaero21.fuc.entity.model.DeviceData

data class SmallDeviceState(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val phoneInfo: String
) {
    constructor(device: DeviceData = DeviceData()): this(
        id = device.id ?: 0,
        title = device.title,
        imageUrl = getImageUrl(device.codename),
        description = "${device.codename} on Android ${device.android} (${device.type})",
        phoneInfo = "Serial number: ${device.sn}"
    )
}

// TODO: Bring ALL models here
private fun getImageUrl(codename: String): String {
    val number = when(codename) {
        "M3910" -> 196
        "M3810" -> 195
        "M2110" -> 194
        "M1100" -> 193
        "M1920" -> 192
        "M1820" -> 191
        "M1720" -> 190
        "M1910" -> 189
        "M1810" -> 188
        "M0910" -> 187
        "M0810" -> 186
        "M9280", "M9285" -> 185
        "M9730" -> 184
        "M9260", "M9265" -> 183
        "M9710", "M9715" -> 182
        "M9230", "M9235" -> 181
        "M8220", "M8225" -> 180
        "M8520", "M8525" -> 179
        "M8160" -> 178
        "M8130" -> 177
        "M8720", "M8725" -> 176
        "M8920", "16thPlus" -> 175
        "M8820", "M8825", "16th" -> 174
        else -> null
    }
    return number?.let { "https://www-res.flyme.cn/resources/flymeos/upload/phonebase/phone_image_$it.png" } ?: ""
}