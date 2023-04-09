package ru.transaero21.fuc.data.repo.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.transaero21.fuc.remote.DEFAULT_HOST
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val context: Context
) : ISettingsRepository {
    private val Context.dataStore by preferencesDataStore(
        name = context.packageName + "_preferences",
    )

    private val prefKeyIsV2Enabled = booleanPreferencesKey("is_v2_enabled")
    private val prefKeyDefaultHost = stringPreferencesKey("default_host")

    override val isV2Enabled: Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[prefKeyIsV2Enabled] ?: false }
    override val defaultHost: Flow<String> =
        context.dataStore.data.map { prefs -> prefs[prefKeyDefaultHost] ?: DEFAULT_HOST }

    override fun enableV2(enable: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[prefKeyIsV2Enabled] = enable
            }
        }
    }

    override fun setDefaultHost(host: String) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[prefKeyDefaultHost] = host
            }
        }
    }
}