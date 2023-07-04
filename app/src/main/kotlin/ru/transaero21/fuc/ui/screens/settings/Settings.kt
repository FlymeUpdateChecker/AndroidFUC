package ru.transaero21.fuc.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.transaero21.fuc.ui.screens.settings.scaffold.SettingsContent
import ru.transaero21.fuc.ui.screens.settings.scaffold.SettingsTopBar
import ru.transaero21.fuc.vm.settings.ISettingsViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Settings(
    settingsVM: ISettingsViewModel,
    goBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val params by settingsVM.params.collectAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SettingsTopBar(goBack = goBack, scrollBehavior = scrollBehavior) }
    ) { paddingValues ->
        SettingsContent(
            paddingValues = paddingValues,
            isV2Enabled = params.isV2Enabled,
            enableV2 = settingsVM::enableV2,
            defaultHost =  params.defaultHost,
            setDefaultHost = settingsVM::setDefaultHost
        )
    }
}
