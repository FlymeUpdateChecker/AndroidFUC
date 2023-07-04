package ru.transaero21.fuc.ui.common.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerDialog(
    datePickerState: DatePickerState,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
    Box {
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = onConfirm,
                    enabled = confirmEnabled
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissRequest
                ) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

}