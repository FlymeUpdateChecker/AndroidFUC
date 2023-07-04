package ru.transaero21.fuc.ui.screens.create

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import ru.transaero21.fuc.ui.screens.create.scaffold.CreateBottomBar
import ru.transaero21.fuc.ui.screens.create.scaffold.CreateContent
import ru.transaero21.fuc.ui.screens.create.scaffold.CreateTopBar
import ru.transaero21.fuc.vm.create.ICreateViewModel

@Composable
fun Create(
    createVM: ICreateViewModel,
    goBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val device by createVM.device.collectAsState()
    Scaffold(
        topBar = {
            CreateTopBar(
                goBack = goBack,
                resetDefaults = createVM::resetDefaults,
                syncData = createVM::syncData
            )
        },
        bottomBar = {
            CreateBottomBar(
                title = device.title,
                verifyPrimary = createVM::verifyPrimary,
                saveDevice = { createVM.saveDevice().also { if (it) goBack() } },
            )
        }
    ) { paddingValues ->
        CreateContent(
            paddingValues = paddingValues,
            device = device
        )
    }
    BackHandler {
        focusManager.clearFocus()
        goBack()
    }
}
