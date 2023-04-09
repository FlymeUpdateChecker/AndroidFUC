package ru.transaero21.fuc.utils

import kotlin.reflect.KFunction
import kotlin.reflect.KProperty1
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object Utils {
    fun getAnyMember(clazz: Any, name: String): KProperty1<out Any, *>? {
        val member = clazz::class.memberProperties.firstOrNull { f -> f.name == name }
        member?.apply {
            isAccessible = true
        }
        return member
    }

    fun getAnyFunction(clazz: Any, name: String): KFunction<*>? {
        val function = clazz::class.functions.firstOrNull { f -> f.name == name }
        function?.apply {
            isAccessible = true
        }
        return function
    }
}
