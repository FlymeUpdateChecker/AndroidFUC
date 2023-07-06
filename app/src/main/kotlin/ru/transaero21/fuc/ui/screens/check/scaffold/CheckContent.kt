package ru.transaero21.fuc.ui.screens.check.scaffold

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.transaero21.fuc.R
import ru.transaero21.fuc.entity.enums.RequestStatus
import ru.transaero21.fuc.entity.state.FirmwareState
import ru.transaero21.fuc.ui.screens.check.card.FirmwareInfoCard
import ru.transaero21.fuc.utils.copy
import ru.transaero21.fuc.utils.share

@Composable
fun CheckContent(
    paddingValues: PaddingValues,
    status: RequestStatus,
    firmware: FirmwareState,
    viewChangeLog: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (status) {
            RequestStatus.LOADING -> LoadingVariant()
            RequestStatus.FOUND_NEW -> FoundNewVariant(
                firmware = firmware,
                viewChangeLog = viewChangeLog
            )
            RequestStatus.NOTHING_NEW -> NothingNewVariant(
                firmware = firmware,
                viewChangeLog = viewChangeLog
            )
            else -> {
                Text(text = status.name)
            }
        }
    }
}

@Composable
private fun LoadingVariant() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun FoundNewVariant(
    firmware: FirmwareState,
    viewChangeLog: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                painter = painterResource(id = R.drawable.check_found_new),
                contentDescription = null
            )
            Text(
                modifier = Modifier.wrapContentSize(),
                text = firmware.version,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = firmware.releaseTime,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 34.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            FirmwareInfoCard(
                icon = Icons.Outlined.Android,
                text = "Android version - ${firmware.android}"
            )
            FirmwareInfoCard(
                icon = Icons.Outlined.FileDownload,
                text = "File size - ${firmware.packageSize}"
            )
        }
        Column(
            Modifier.padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onClick = viewChangeLog
            ) {
                Text(text = "View changelog")
            }
            ElevatedCard(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    modifier = Modifier.padding(14.dp),
                    text = firmware.packageUrl,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 14.sp)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(26.dp)) {
                val context = LocalContext.current
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        context.share(
                            context.getString(
                                R.string.check_share_text,
                                firmware.version,
                                firmware.packageUrl
                            )
                        )
                    }
                ) {
                    Text(text = "Share")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        context.copy(text = firmware.packageUrl)
                    }
                ) {
                    Text(text = "Copy link")
                }
            }
        }
    }
}

@Composable 
private fun NothingNewVariant(
    firmware: FirmwareState,
    viewChangeLog: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.defaultMinSize(minHeight = 78.dp)) {
                if (firmware.changeLog.isNotEmpty()) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 10.dp, horizontal = 14.dp),
                        onClick = viewChangeLog
                    ) {
                        Text(text = "View current changelog")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                painter = painterResource(id = R.drawable.check_nothing_new),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "Nothing new found!",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}