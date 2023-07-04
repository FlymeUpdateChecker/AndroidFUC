package ru.transaero21.fuc.ui.common.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.entity.state.FieldState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InputDialog(
    title: String,
    description: String,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    input: FieldState,
    onEnter: () -> Boolean
) {
    if (expanded) {
        AlertDialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = AlertDialogDefaults.shape,
                color = AlertDialogDefaults.containerColor,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.Start)
                    )
                    OutlinedTextField(
                        value = input.value,
                        onValueChange = input.setValue,
                        label = { Text(text = description) },
                        isError = input.isError,
                        singleLine = true
                    )
                    TextButton(
                        onClick = { if (onEnter()) onDismissRequest() },
                        enabled = input.value.isNotBlank(),
                        modifier = Modifier.align(Alignment.End).padding(top = 18.dp)
                    ) {
                        Text(
                            text = "Enter",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
