package ru.transaero21.fuc.ui.screens.device

import android.view.HapticFeedbackConstants
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import ru.transaero21.fuc.entity.model.DeviceData
import ru.transaero21.fuc.ui.screens.device.dialogs.SaveDialog
import ru.transaero21.fuc.ui.screens.device.scaffold.DeviceBottomBar
import ru.transaero21.fuc.ui.screens.device.scaffold.DeviceContent
import ru.transaero21.fuc.ui.screens.device.scaffold.DeviceTopBar
import ru.transaero21.fuc.vm.device.IDeviceViewModel

@Composable
fun Device(
    deviceVM: IDeviceViewModel,
    goCheck: (DeviceData) -> Unit,
    goBack: () -> Unit
) {
    val view = LocalView.current
    val focusManager = LocalFocusManager.current
    val device by deviceVM.device.collectAsState()
    val isChanged by deviceVM.isChanged.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val onExit = { if (isChanged) (if (!expanded) {
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        expanded = true
    }) else {
        focusManager.clearFocus()
        goBack()
    } }
    Scaffold(
        topBar = {
            DeviceTopBar(
                title = device.title.value,
                isChanged = isChanged,
                save = deviceVM::saveDevice,
                restore = deviceVM::restoreOriginal,
                goBack = onExit
            )
        },
        bottomBar = {
            DeviceBottomBar(
                device = device,
                verifyPrimary = deviceVM::verifyPrimary,
                goCheck = goCheck
            )
        }
    ) { paddingValues ->
        DeviceContent(
            paddingValues = paddingValues,
            device = device
        )
    }
    BackHandler(enabled = isChanged, onBack = onExit)
    SaveDialog(expanded = expanded, onDismissRequest = { expanded = false }, goBack = goBack)
}