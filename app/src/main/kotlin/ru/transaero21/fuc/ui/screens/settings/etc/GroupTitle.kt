package ru.transaero21.fuc.ui.screens.settings.etc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GroupTitle(title: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 2.dp, start = 16.dp),
        text = title,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary
    )
}