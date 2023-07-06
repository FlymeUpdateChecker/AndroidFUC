package ru.transaero21.fuc.data.repo.settings

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.transaero21.fuc.remote.DEFAULT_HOST
import javax.inject.Inject

private const val TAG = "SettingsRepository"

class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ISettingsRepository {
    private val Context.dataStore by preferencesDataStore(
        name = context.packageName + "_preferences",
    )

    private val prefKeyIsV2Enabled = booleanPreferencesKey("is_v2_enabled")
    private val prefKeyDefaultHost = stringPreferencesKey("default_host")

    override val isV2Enabled: Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[prefKeyIsV2Enabled] ?: true }
    override val defaultHost: Flow<String> =
        context.dataStore.data.map { prefs -> prefs[prefKeyDefaultHost] ?: DEFAULT_HOST }

    init { Log.d(TAG, "Initialize") }

    override fun enableV2(enable: Boolean) {
        Log.d(TAG, "enableV2: Called with: enable=$enable.")
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[prefKeyIsV2Enabled] = enable
            }
        }
    }

    override fun setDefaultHost(host: String) {
        Log.d(TAG, "setDefaultHost: Called with: host=$host.")
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { prefs ->
                prefs[prefKeyDefaultHost] = host
            }
        }
    }
}