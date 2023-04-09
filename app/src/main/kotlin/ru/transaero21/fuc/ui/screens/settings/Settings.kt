package ru.transaero21.fuc.ui.screens.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.transaero21.fuc.ui.screens.settings.scaffold.SettingsContent
import ru.transaero21.fuc.ui.screens.settings.scaffold.SettingsTopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Settings(
    goBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = { SettingsTopBar(goBack = goBack, scrollBehavior = scrollBehavior) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        SettingsContent(paddingValues = paddingValues)
    }
}