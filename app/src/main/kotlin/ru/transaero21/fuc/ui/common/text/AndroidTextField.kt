package ru.transaero21.fuc.ui.common.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.ui.common.menus.AndroidMenu

@Composable
fun AndroidTextField(android: FieldState) {
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.wrapContentSize()) {
        OutlinedTextField(
            value = android.value,
            onValueChange = android.setValue,
            trailingIcon = {
                var expanded by remember { mutableStateOf(false) }
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = when (expanded) {
                            true -> Icons.Default.ArrowDropUp
                            false -> Icons.Default.ArrowDropDown
                        },
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.align(Alignment.TopEnd)) {
                    AndroidMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        setAndroidValue = android.setValue
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Right)
            }),
            label = { Text(text = stringResource(R.string.text_android_title)) },
            isError = android.isError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}