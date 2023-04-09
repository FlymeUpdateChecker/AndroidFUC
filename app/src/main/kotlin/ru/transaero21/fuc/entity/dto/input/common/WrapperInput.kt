package ru.transaero21.fuc.entity.dto.input.common

import kotlinx.serialization.Serializable
import java.math.BigInteger
import java.security.MessageDigest

@Serializable
abstract class WrapperInput {
    abstract val sign: String
    val unitType: String = "0"
}

fun String.md5Sign(key: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest((this + key).toByteArray())).toString(16).padStart(32, '0')
}

