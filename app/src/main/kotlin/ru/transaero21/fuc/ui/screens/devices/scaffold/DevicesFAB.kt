package ru.transaero21.fuc.ui.screens.devices.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.transaero21.fuc.R

@Composable
fun DevicesFAB(
    addDevice: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = addDevice,
        icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        text = { Text(text = stringResource(id = R.string.devices_add_device)) }
    )
}