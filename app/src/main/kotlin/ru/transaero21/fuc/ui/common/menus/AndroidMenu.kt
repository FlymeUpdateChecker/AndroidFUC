package ru.transaero21.fuc.ui.common.menus

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.transaero21.fuc.entity.enums.Android

@Composable
fun AndroidMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    setAndroidValue: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        Android.values().forEach { android ->
            DropdownMenuItem(
                text = { Text(text = "${android.version} (${android.api})") },
                onClick = {
                    setAndroidValue(android.version)
                    onDismissRequest()
                }
            )
        }
    }
}