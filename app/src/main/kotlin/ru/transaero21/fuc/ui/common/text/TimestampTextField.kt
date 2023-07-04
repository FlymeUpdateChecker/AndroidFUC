package ru.transaero21.fuc.ui.common.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.ui.common.dialogs.TimePickerDialog
import ru.transaero21.fuc.ui.common.dialogs.DatePickerDialog

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TimestampTextField(timestamp: FieldState) {
    val focusManager = LocalFocusManager.current
    val timePickerState = rememberTimePickerState(
        initialHour = (timestamp.value.toLongOrNow() % (24 * 60 * 60) / (60 * 60)).toInt(),
        initialMinute = (timestamp.value.toLongOrNow() % (24 * 60 * 60) % (60 * 60) / 60).toInt(),
        is24Hour = true
    )
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = timestamp.value.toLongOrNow() * 1000L
    )
    Box(modifier = Modifier.wrapContentSize()) {
        OutlinedTextField(
            value = timestamp.value,
            onValueChange = timestamp.setValue,
            trailingIcon = {
                var openDatePicker by remember { mutableStateOf(false) }
                var openTimePicker by remember { mutableStateOf(false) }
                if (openDatePicker) {
                    DatePickerDialog(
                        datePickerState = datePickerState,
                        onDismissRequest = { openDatePicker = false },
                        onConfirm = {
                            openDatePicker = false
                            openTimePicker = true
                        }
                    )
                }
                if (openTimePicker) {
                    TimePickerDialog(
                        timePickerState = timePickerState,
                        onDismissRequest = { openTimePicker = false },
                        onConfirm = {
                            openTimePicker = false
                            datePickerState.selectedDateMillis?.let { dateMillis ->
                                timestamp.setValue((dateMillis / 1000L + timePickerState.hour * 60 + timePickerState.minute * 60).toString())
                            }
                        }
                    )
                }
                IconButton(onClick = { openDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null
                    )
                }
            },
            label = { Text(text = stringResource(R.string.text_timestamp_title)) },
            isError = timestamp.isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun String.toLongOrNow(): Long {
    return this.toLongOrNull() ?: (System.currentTimeMillis() / 1000L)
}