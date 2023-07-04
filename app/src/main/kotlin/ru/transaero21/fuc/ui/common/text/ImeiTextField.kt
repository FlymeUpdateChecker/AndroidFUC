package ru.transaero21.fuc.ui.common.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.state.FieldState

@Composable
fun ImeiTextField(imei: FieldState) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = imei.value,
        onValueChange = imei.setValue,
        label = { Text(text = stringResource(R.string.text_imei_title)) },
        isError = imei.isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}