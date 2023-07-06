package ru.transaero21.fuc.ui.screens.check

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.ui.screens.check.scaffold.CheckContent
import ru.transaero21.fuc.ui.screens.check.scaffold.CheckTopBar
import ru.transaero21.fuc.vm.check.ICheckViewModel

@Composable
fun Check(
    checkVM: ICheckViewModel,
    viewChangeLog: () -> Unit,
    goBack: () -> Unit
) {
    val status by checkVM.status.collectAsState()
    val firmware by checkVM.firmware.collectAsState()
    Scaffold(
        topBar = { CheckTopBar(goBack = goBack) },
        bottomBar = { Spacer(modifier = Modifier.size(0.dp)) }
    ) { paddingValues ->
        CheckContent(
            paddingValues = paddingValues,
            status = status,
            firmware = firmware,
            viewChangeLog = viewChangeLog
        )
    }
    BackHandler(onBack = goBack)
}