package ru.transaero21.fuc.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import ru.transaero21.fuc.R

private var clipboardManager: ClipboardManager? = null

fun Context.copy(label: CharSequence = "", text: CharSequence) {
    if (clipboardManager == null) {
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
    clipboardManager?.setPrimaryClip(ClipData.newPlainText(label, text))
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) toast(getString(R.string.utils_on_copied))
}