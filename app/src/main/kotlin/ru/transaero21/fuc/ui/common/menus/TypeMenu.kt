package ru.transaero21.fuc.ui.common.menus

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.transaero21.fuc.entity.enums.Type

@Composable
fun TypeMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    setTypeValue: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        Type.values().forEach { type ->
            DropdownMenuItem(
                text = { Text(text = type.type) },
                onClick = {
                    setTypeValue(type.type)
                    onDismissRequest()
                }
            )
        }
    }
}