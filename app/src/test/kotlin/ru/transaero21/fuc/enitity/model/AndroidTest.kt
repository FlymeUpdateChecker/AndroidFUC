package ru.transaero21.fuc.enitity.model

import org.junit.Test
import ru.transaero21.fuc.entity.defaults.Android

class AndroidTest {
    @Test
    fun android_isVersionsContainsNumbersAndDots_isValid() {
        for (android in Android.values()) {
            assert(Regex("^\\d+(\\.\\d+)*\$").matches(android.version))
        }
    }
}