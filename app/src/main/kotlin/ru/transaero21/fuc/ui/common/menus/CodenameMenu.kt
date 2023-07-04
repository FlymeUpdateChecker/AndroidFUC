package ru.transaero21.fuc.ui.common.menus

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.transaero21.fuc.entity.enums.Devices

@Composable
fun CodenameMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    setCodenameValue: (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        Devices.values().forEach { device ->
            DropdownMenuItem(
                text = { Text(text = device.device) },
                onClick = {
                    setCodenameValue(device.codename)
                    onDismissRequest()
                }
            )
        }
    }
}