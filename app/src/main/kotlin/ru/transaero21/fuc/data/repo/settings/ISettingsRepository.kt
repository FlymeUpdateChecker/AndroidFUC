package ru.transaero21.fuc.data.repo.settings

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    val isV2Enabled: Flow<Boolean>
    val defaultHost: Flow<String>

    fun enableV2(enable: Boolean)
    fun setDefaultHost(host: String)
}