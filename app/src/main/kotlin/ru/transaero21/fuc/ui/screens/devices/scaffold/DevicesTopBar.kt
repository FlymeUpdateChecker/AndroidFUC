package ru.transaero21.fuc.ui.screens.devices.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.transaero21.fuc.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DevicesTopBar(
    goBack: (() -> Unit)?,
    openSettings: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.devices_saved_devices)) },
        navigationIcon = {
            goBack?.let {
                IconButton(onClick = goBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            IconButton(onClick = openSettings) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
        }
    )
}