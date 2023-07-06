package ru.transaero21.fuc.data.repo.check

import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.entity.enums.RequestStatus
import ru.transaero21.fuc.entity.model.DeviceData

interface ICheckRepository {
    suspend fun checkSys(data: DeviceData, isV2: Boolean, callback: (SuccessInfo?, RequestStatus) -> Unit)
    suspend fun cancelCheck()
}