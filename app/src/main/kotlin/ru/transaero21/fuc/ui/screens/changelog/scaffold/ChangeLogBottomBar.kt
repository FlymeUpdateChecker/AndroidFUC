package ru.transaero21.fuc.ui.screens.changelog.scaffold

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.Spanned
import android.view.HapticFeedbackConstants
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import org.jsoup.Jsoup
import ru.transaero21.fuc.R
import ru.transaero21.fuc.utils.copy

@Composable
fun ChangeLogBottomBar(changeLog: String) {
    val context = LocalContext.current
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp, horizontal = 14.dp),
        onClick = {
            context.copy(text = changeLog.fromHtml())
        }
    ) {
        Text(text = stringResource(id = R.string.changelog_copy_button))
    }
}

private fun String.fromHtml(): Spanned {
    val bodyHtml = Jsoup.parse(this).body().html()
    return HtmlCompat.fromHtml(bodyHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
}