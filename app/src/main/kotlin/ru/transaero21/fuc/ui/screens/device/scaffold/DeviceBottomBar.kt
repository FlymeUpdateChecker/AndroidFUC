package ru.transaero21.fuc.ui.screens.device.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.entity.state.DeviceState

@Composable
fun DeviceBottomBar(
    device: DeviceState,
    verifyPrimary: () -> Boolean,
    goCheck: (DeviceData) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 10.dp, horizontal = 14.dp)
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {
                focusManager.clearFocus()
                device.let {
                    if (verifyPrimary()) goCheck(it.toData(it.id))
                }
            }
        ) {
            Text(text = stringResource(R.string.device_check_button))
        }
    }
}