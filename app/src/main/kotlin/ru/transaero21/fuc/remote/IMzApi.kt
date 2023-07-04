package ru.transaero21.fuc.remote

import ru.transaero21.fuc.entity.dto.info.common.MzInfo
import ru.transaero21.fuc.entity.dto.input.v1.SysV1Input
import ru.transaero21.fuc.entity.dto.input.v2.SysV2Input

const val DEFAULT_HOST = "https://upush.meizu.com"

const val SIGN_V1 = "2635881a7ab0593849fe89e685fc56cd"
const val SIGN_V2 = "325POre45f12iplghn196yUTrhgvcxAz"

interface IMzApi {
    suspend fun checkSysV1(sys: SysV1Input, host: String): MzInfo?
    suspend fun checkSysV2(sys: SysV2Input, host: String): MzInfo?
}
