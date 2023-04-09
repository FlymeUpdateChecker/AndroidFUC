package ru.transaero21.fuc.entity.dto.input.v2

import kotlinx.serialization.Serializable
import ru.transaero21.fuc.entity.dto.input.common.WrapperInput
import ru.transaero21.fuc.entity.dto.input.common.md5Sign
import ru.transaero21.fuc.remote.SIGN_V1
import ru.transaero21.fuc.remote.SIGN_V2

@Serializable
data class SysV2Input(
    val sys: String,
    override val sign: String = sys.md5Sign(SIGN_V2)
) : WrapperInput()