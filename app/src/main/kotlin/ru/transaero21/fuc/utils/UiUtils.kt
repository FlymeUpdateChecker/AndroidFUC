package ru.transaero21.fuc.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var toastMsg: Toast? = null
private val scope = CoroutineScope(Dispatchers.Main)

fun Context.toast(message: CharSequence) {
    toastMsg?.cancel()
    scope.launch {
        toastMsg = Toast.makeText(this@toast, message, Toast.LENGTH_SHORT)
        toastMsg?.show()
    }
}