package ru.transaero21.fuc.ui.common.etc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DeviceInputSection(
    imageVector: ImageVector?,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 9.dp)
                .width(32.dp)
                .height(TextFieldDefaults.MinHeight)
        ) {
            if (imageVector != null) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    imageVector = imageVector,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        content.invoke()
    }
}