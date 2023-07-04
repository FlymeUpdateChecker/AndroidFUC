package ru.transaero21.fuc.entity.enums

enum class Android(val version: String, val api: Int) {
    OatmealCookie(version = "8.0", api = 26),
    OatmealCookieV2(version = "8.1", api = 27),
    PistachioIceCream(version = "9", api = 28),
    QuinceTart(version = "10", api = 29),
    RedVelvetCake(version = "11", api = 30),
    SnowCone(version = "12", api = 31),
    SnowConeV2(version = "12.1", api = 32),
    Tiramisu(version = "13", api = 33),
    UpsideDownCake(version = "14", api = 34)
}