package ru.transaero21.fuc.ui.screens.devices.cards

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import ru.transaero21.fuc.entity.state.FieldState
import ru.transaero21.fuc.entity.state.SmallDeviceState
import ru.transaero21.fuc.ui.common.dialogs.InputDialog
import ru.transaero21.fuc.ui.screens.devices.menus.DeviceMenu

@Composable
fun DeviceCard(
    modifier: Modifier = Modifier,
    device: SmallDeviceState,
    field: FieldState,
    deleteDevice: (Int) -> Unit,
    setTitle: (Int) -> Boolean,
    onClick: (Int) -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current
    val density = LocalDensity.current
    var expanded by remember { mutableStateOf(false) }
    var itemHeight by remember { mutableStateOf(0.dp) }
    var offset by remember { mutableStateOf(DpOffset.Zero) }
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .indication(interactionSource, LocalIndication.current)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                        offset = DpOffset(it.x.toDp(), it.y.toDp())
                        expanded = true
                    },
                    onTap = { onClick(device.id) },
                    onPress = {
                        PressInteraction
                            .Press(it)
                            .let { press ->
                                interactionSource.emit(press)
                                tryAwaitRelease()
                                interactionSource.emit(PressInteraction.Release(press))
                            }
                    }
                )
            }
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
            .padding(vertical = 14.dp, horizontal = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(device.imageUrl)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(68.dp)
                    .wrapContentSize(),
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(start = 4.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = device.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                )
                Text(
                    text = device.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                )
                Text(
                    text = device.phoneInfo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                )
            }
        }
        var lExpanded by remember { mutableStateOf(false) }
        DeviceMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            onRename = { lExpanded = true },
            deleteDevice = { deleteDevice(device.id) },
            offset = offset.copy(y = offset.y - itemHeight)
        )
        LaunchedEffect(lExpanded) { field.setValue(device.title) }
        InputDialog(
            title = "Rename title",
            description = "Title",
            expanded = lExpanded,
            onDismissRequest = { lExpanded = false },
            input = field,
            onEnter = { setTitle(device.id) }
        )
    }
}