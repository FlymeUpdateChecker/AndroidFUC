package ru.transaero21.fuc.ui.screens.create.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.state.DeviceState
import ru.transaero21.fuc.ui.common.etc.DeviceInput

@Composable
fun CreateContent(
    paddingValues: PaddingValues,
    device: DeviceState
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(4.dp),
            painter = painterResource(R.drawable.create_config),
            contentDescription = null
        )
        DeviceInput(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            codename = device.codename,
            imei = device.imei,
            sn = device.sn,
            android = device.android,
            type = device.type,
            timestamp = device.timestamp,
            host = device.host
        )
    }
}