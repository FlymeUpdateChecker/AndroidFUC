package ru.transaero21.fuc.ui.screens.devices

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesContent
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesFAB
import ru.transaero21.fuc.ui.screens.devices.scaffold.DevicesTopBar
import ru.transaero21.fuc.vm.devices.IDevicesViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Devices(
    devicesVM: IDevicesViewModel,
    openCreate: () -> Unit,
    openSettings: () -> Unit,
    openDevice: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val devicesList by devicesVM.devicesList.collectAsState()
    val renameField by devicesVM.renameField.collectAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { DevicesTopBar(openSettings = openSettings, scrollBehavior = scrollBehavior) },
        floatingActionButton = { DevicesFAB(openCreate = openCreate) },
        bottomBar = { Spacer(modifier = Modifier.size(0.dp)) }
    ) { paddingValues ->
        DevicesContent(
            paddingValues = paddingValues,
            devicesList = devicesList,
            renameField = renameField,
            openDevice = openDevice,
            setTitle = devicesVM::setTitle,
            deleteDevice = devicesVM::deleteDevice
        )
    }
}