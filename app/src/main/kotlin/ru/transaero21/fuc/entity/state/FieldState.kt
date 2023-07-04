package ru.transaero21.fuc.entity.state

data class FieldState(
    var value: String,
    var setValue: (String) -> Unit = {},
    val isError: Boolean = false
)
