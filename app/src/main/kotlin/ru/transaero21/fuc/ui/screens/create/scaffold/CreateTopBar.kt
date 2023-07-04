package ru.transaero21.fuc.ui.screens.create.scaffold

import android.view.HapticFeedbackConstants
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import ru.transaero21.fuc.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CreateTopBar(
    goBack: () -> Unit,
    resetDefaults: () -> Unit = {},
    syncData: () -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = stringResource(R.string.create_title)) },
        navigationIcon = {
            IconButton(onClick = goBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    Toast.makeText(context, "Restored defaults", Toast.LENGTH_SHORT).show()
                    resetDefaults()
                }
            ) {
                Icon(imageVector = Icons.Default.Sync, contentDescription = null)
            }
            IconButton(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                    Toast.makeText(context, "Synced with device", Toast.LENGTH_SHORT).show()
                    syncData()
                }
            ) {
                Icon(imageVector = Icons.Default.AutoFixHigh, contentDescription = null)
            }
        }
    )
}