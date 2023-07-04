package ru.transaero21.fuc.ui.common.etc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.ui.common.text.AndroidTextField
import ru.transaero21.fuc.ui.common.text.CodenameTextField
import ru.transaero21.fuc.ui.common.text.HostTextField
import ru.transaero21.fuc.ui.common.text.ImeiTextField
import ru.transaero21.fuc.ui.common.text.SnTextField
import ru.transaero21.fuc.ui.common.text.TimestampTextField
import ru.transaero21.fuc.ui.common.text.TypeTextField

@Composable
fun DeviceInput(
    modifier: Modifier = Modifier,
    codename: FieldState,
    sn: FieldState,
    imei: FieldState,
    android: FieldState,
    type: FieldState,
    timestamp: FieldState,
    host: FieldState
) {
    Column(
        modifier = modifier.padding(horizontal = 18.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        DeviceInputSection(imageVector = Icons.Outlined.Smartphone) {
            CodenameTextField(codename = codename)
        }
        DeviceInputSection(imageVector = null) {
            SnTextField(sn = sn)
        }
        DeviceInputSection(imageVector = null) {
            ImeiTextField(imei = imei)
        }
        DeviceInputSection(imageVector = Icons.Outlined.Info) {
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    AndroidTextField(android = android)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    TypeTextField(type = type)
                }
            }
        }
        DeviceInputSection(imageVector = null) {
            TimestampTextField(timestamp = timestamp)
        }
        DeviceInputSection(imageVector = Icons.Outlined.Dns) {
            HostTextField(host = host)
        }
    }
}