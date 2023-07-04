package ru.transaero21.fuc.ui.screens.create.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.ui.common.dialogs.InputDialog

@Composable
fun CreateBottomBar(
    title: FieldState,
    verifyPrimary: () -> Boolean,
    saveDevice: () -> Boolean,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 10.dp, horizontal = 14.dp)
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {
                focusManager.clearFocus()
                expanded = verifyPrimary()
            }
        ) {
            Text(text = stringResource(R.string.create_save_button))
        }
    }
    InputDialog(
        title = "Input title",
        description = "Title",
        expanded = expanded,
        onDismissRequest = { expanded = false },
        input = title,
        onEnter = {
            focusManager.clearFocus()
            return@InputDialog saveDevice()
        }
    )
}