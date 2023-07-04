package ru.transaero21.fuc.ui.screens.devices.scaffold

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.entity.state.SmallDeviceState
import ru.transaero21.fuc.ui.screens.devices.cards.DeviceCard

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun DevicesContent(
    paddingValues: PaddingValues,
    devicesList: List<SmallDeviceState>,
    renameField: FieldState,
    openDevice: (Int) -> Unit,
    setTitle: (Int) -> Boolean,
    deleteDevice: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = devicesList.size, key = { devicesList[it].id }) {
                DeviceCard(
                    modifier = Modifier.animateItemPlacement(tween(durationMillis = 250)),
                    device = devicesList[it],
                    field = renameField,
                    deleteDevice = deleteDevice,
                    setTitle = setTitle,
                    onClick = openDevice
                )
            }
        }
    }
}