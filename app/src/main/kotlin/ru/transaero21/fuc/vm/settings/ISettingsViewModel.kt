package ru.transaero21.fuc.vm.settings

import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.entity.state.ParamsState

interface ISettingsViewModel {
    val params: StateFlow<ParamsState>

    fun enableV2(enable: Boolean)
    fun setDefaultHost(host: String)
}