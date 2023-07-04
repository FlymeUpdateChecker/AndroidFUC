package ru.transaero21.fuc.ui.screens.device.scaffold

import android.view.HapticFeedbackConstants
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DeviceTopBar(
    goBack: () -> Unit,
    isChanged: Boolean,
    restore: () -> Unit,
    save: () -> Boolean,
    title: String
) {
    val view = LocalView.current
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = goBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    Toast.makeText(context, "Restored", Toast.LENGTH_SHORT).show()
                    restore()
                },
                enabled = isChanged
            ) {
                Icon(imageVector = Icons.Default.Sync, contentDescription = null)
            }
            IconButton(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    Toast.makeText(context, if (save()) "Saved" else "Save error", Toast.LENGTH_SHORT).show()
                },
                enabled = isChanged
            ) {
                Icon(imageVector = Icons.Outlined.Save, contentDescription = null)
            }
        }
    )
}