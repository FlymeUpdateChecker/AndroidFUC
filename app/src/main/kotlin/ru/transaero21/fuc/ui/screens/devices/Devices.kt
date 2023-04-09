package ru.transaero21.fuc.ui.screens.devices

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesContent
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesFAB
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesTopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DevicesScreen(
    goBack: (() -> Unit)?,
    addDevice: () -> Unit,
    openSettings: () -> Unit,
) {
    Scaffold(
        topBar = { DevicesTopBar(goBack = goBack, openSettings = openSettings) },
        floatingActionButton = { DevicesFAB(addDevice = addDevice)}
    ) { paddingValues ->
        DevicesContent(paddingValues = paddingValues)
    }
}