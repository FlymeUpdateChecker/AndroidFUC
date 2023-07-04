package ru.transaero21.fuc.ui.screens.device.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SaveDialog(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    goBack: () -> Unit,
) {
    if (expanded) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = "Unsaved data") },
            text = {
                Text("The current configuration of the device has unsaved data, it might be worth saving it or you will lose it")
            },
            confirmButton = {
                TextButton(
                    onClick = { onDismissRequest() }
                ) { Text("Cancel") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                        goBack()
                    }
                ) { Text("Exit") }
            }
        )
    }
}