package ru.transaero21.fuc.data.repo.check

import android.util.Log
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import ru.transaero21.fuc.entity.dto.info.common.MzInfo
import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.entity.dto.input.v1.FirmwareV1Input
import ru.transaero21.fuc.entity.dto.input.v1.SysV1Input
import ru.transaero21.fuc.entity.dto.input.v2.FirmwareV2Input
import ru.transaero21.fuc.entity.dto.input.v2.SysV2Input
import ru.transaero21.fuc.entity.enums.RequestStatus
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.remote.IMzApi
import javax.inject.Inject

class CheckRepository @Inject constructor(
    private val mzApi: IMzApi
) : ICheckRepository {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    override suspend fun checkSys(
        data: DeviceData,
        isV2: Boolean,
        callback: (SuccessInfo?, RequestStatus) -> Unit
    ) {
        job?.cancelAndJoin()
        job = scope.launch {
            callback(null, RequestStatus.LOADING)
            val host = Url(data.host).host
            val mzInfo = if (isV2) {
                mzApi.checkSysV2(SysV2Input(FirmwareV2Input(data)), host)
            } else {
                mzApi.checkSysV1(SysV1Input(FirmwareV1Input(data)), host)
            }
            mzInfo?.let {
                if (mzInfo is SuccessInfo) {
                    if (mzInfo.reply.value.new != null) callback(mzInfo, RequestStatus.FOUND_NEW)
                    else callback(mzInfo, RequestStatus.NOTHING_NEW)
                }
                else callback(null, RequestStatus.ERROR)
            } ?: run { callback(null, RequestStatus.FAILURE) }
        }
    }

}