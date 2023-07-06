package ru.transaero21.fuc.ui.screens.changelog

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.transaero21.fuc.ui.screens.changelog.scaffold.ChangeLogBottomBar
import ru.transaero21.fuc.ui.screens.changelog.scaffold.ChangeLogContent
import ru.transaero21.fuc.ui.screens.changelog.scaffold.ChangeLogTopBar
import ru.transaero21.fuc.vm.check.ICheckViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ChangeLog(
    checkVM: ICheckViewModel,
    goBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val firmware by checkVM.firmware.collectAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { ChangeLogTopBar(goBack = goBack, scrollBehavior = scrollBehavior) },
        bottomBar = { ChangeLogBottomBar(changeLog = firmware.changeLog) }
    ) { paddingValues ->
        ChangeLogContent(paddingValues = paddingValues, firmware = firmware)
    }
    BackHandler(onBack = goBack)
}