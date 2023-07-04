package ru.transaero21.fuc.ui.screens.check.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.transaero21.fuc.entity.enums.RequestStatus

@Composable
fun CheckContent(
    paddingValues: PaddingValues,
    status: RequestStatus
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Text(text = status.name)
    }
}