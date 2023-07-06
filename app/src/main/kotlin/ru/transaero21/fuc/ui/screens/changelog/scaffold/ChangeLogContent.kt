package ru.transaero21.fuc.ui.screens.changelog.scaffold

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.steadystate.css.parser.CSSOMParser
import com.steadystate.css.parser.SACParserCSS3
import org.jsoup.Jsoup
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.CSSStyleRule
import ru.transaero21.fuc.entity.state.FirmwareState
import java.io.StringReader


@Composable
@SuppressLint("SetJavaScriptEnabled")
fun ChangeLogContent(
    paddingValues: PaddingValues,
    firmware: FirmwareState
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        val color = "#" + Integer.toHexString(LocalContentColor.current.toArgb()).drop(2)
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    setBackgroundColor(Color.TRANSPARENT)
                    setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
                }
            }, update = {
                it.loadData(presetColors(firmware.changeLog, color), "text/html", "utf-8")
            },
            modifier = Modifier.verticalScroll(rememberScrollState())
        )
    }
}

private fun presetColors(html: String, color: String): String {
    val doc = Jsoup.parse(html)
    doc.head().select("style").first()?.let { style ->
        val source = InputSource(StringReader(style.data()))
        val sheet = CSSOMParser(SACParserCSS3()).parseStyleSheet(source, null, null)
        sheet.cssRules.let { rules ->
            style.text("")
            for (i in 0 .. rules.length) {
                val rule = rules.item(i)
                if (rule is CSSStyleRule) {
                    rule.style.setProperty("color", color, null)
                    style.append(rule.cssText)
                }
            }
        }
    }
    return doc.html()
}

