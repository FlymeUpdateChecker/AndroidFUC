package ru.transaero21.fuc.ui.screens.settings.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.ui.screens.settings.card.ParamCard
import ru.transaero21.fuc.ui.screens.settings.etc.GroupTitle

@Composable
fun SettingsContent(
    paddingValues: PaddingValues,
    isV2Enabled: Boolean,
    enableV2: (Boolean) -> Unit,
    defaultHost: String,
    setDefaultHost: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            GroupTitle(title = "API")
            ParamCard(
                title = "Use API V2",
                description = "Beware, API V1 may not be available",
                onClick = { /*TODO*/ }
            ) {
                Switch(
                    checked = isV2Enabled,
                    onCheckedChange = { if (isV2Enabled != it) enableV2(it) },
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            ParamCard(
                title = "Default host",
                description = "Default when creating a configuration",
                onClick = { /*TODO*/ }
            )
        }
    }
}