package ru.transaero21.fuc.ui.screens.check

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.transaero21.fuc.ui.screens.check.scaffold.CheckContent
import ru.transaero21.fuc.vm.check.ICheckViewModel

@Composable
fun Check(
    checkVM: ICheckViewModel,
    goBack: () -> Unit
) {
    val status by checkVM.status.collectAsState()
    Scaffold(

    ) { paddingValues ->
        CheckContent(paddingValues = paddingValues, status = status)
    }
}