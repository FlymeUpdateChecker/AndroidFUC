package ru.transaero21.fuc.enitity.dto

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.transaero21.fuc.entity.dto.input.common.md5Sign
import ru.transaero21.fuc.remote.SIGN_V1
import ru.transaero21.fuc.remote.SIGN_V2
import ru.transaero21.fuc.utils.UtilsV1
import ru.transaero21.fuc.utils.UtilsV2

class HashTest {
    @Test
    fun hash_checkHashForSignV1_isCorrect() {
        assertEquals("f501eb1d0fb16b2244de340857786182", Json.encodeToString(UtilsV1.firmware).md5Sign(SIGN_V1))
    }

    @Test
    fun hash_checkHashForSignV2_isCorrect() {
        assertEquals("508f9b5d27bf5b196948051250fe5443", Json.encodeToString(UtilsV2.firmware).md5Sign(SIGN_V2))
    }
}