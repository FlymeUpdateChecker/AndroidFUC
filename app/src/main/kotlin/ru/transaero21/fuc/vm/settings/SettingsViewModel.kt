package ru.transaero21.fuc.vm.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.transaero21.fuc.data.repo.settings.ISettingsRepository
import ru.transaero21.fuc.entity.state.ParamsState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: ISettingsRepository
): ViewModel(), ISettingsViewModel {
    private val _params = MutableStateFlow(ParamsState())
    override val params = _params.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepo.isV2Enabled.collect { enabled ->
                _params.update { it.copy(isV2Enabled = enabled) }
            }
        }
        viewModelScope.launch {
            settingsRepo.defaultHost.collect { host ->
                _params.update { it.copy(defaultHost = host) }
            }
        }
    }

    override fun enableV2(enable: Boolean) = settingsRepo.enableV2(enable)
    override fun setDefaultHost(host: String) = settingsRepo.setDefaultHost(host)
}