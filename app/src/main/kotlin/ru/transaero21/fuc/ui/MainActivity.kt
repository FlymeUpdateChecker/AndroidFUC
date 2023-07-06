package ru.transaero21.fuc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.transaero21.fuc.ui.screens.Screen
import ru.transaero21.fuc.ui.screens.changelog.ChangeLog
import ru.transaero21.fuc.ui.screens.check.Check
import ru.transaero21.fuc.ui.screens.create.Create
import ru.transaero21.fuc.ui.screens.device.Device
import ru.transaero21.fuc.ui.screens.devices.Devices
import ru.transaero21.fuc.ui.screens.settings.Settings
import ru.transaero21.fuc.ui.theme.FUCTheme
import ru.transaero21.fuc.vm.check.CheckViewModel
import ru.transaero21.fuc.vm.create.CreateViewModel
import ru.transaero21.fuc.vm.device.DeviceViewModel
import ru.transaero21.fuc.vm.devices.DevicesViewModel
import ru.transaero21.fuc.vm.settings.SettingsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val createVM by viewModels<CreateViewModel>()
    private val devicesVM by viewModels<DevicesViewModel>()
    private val deviceVM by viewModels<DeviceViewModel>()
    private val settingsVM by viewModels<SettingsViewModel>()
    private val checkVM by viewModels<CheckViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FUCTheme {
                Main()
            }
        }
    }

    @Composable
    private fun Main() {
        val navController = rememberNavController()
        Scaffold(
            contentWindowInsets = WindowInsets.navigationBars
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                MainNav(navController)
            }
        }
        val view = LocalView.current
        val darkTheme = isSystemInDarkTheme()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    @Composable
    private fun MainNav(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screen.Devices.route ) {
            composable(route = Screen.Devices.route) {
                Devices(
                    devicesVM = devicesVM,
                    openCreate = { navController.navigate(route = Screen.Create.route) },
                    openSettings = { navController.navigate(route = Screen.Settings.route) },
                    openDevice = { id ->
                        deviceVM.setDevice(id)
                        navController.navigate(route = Screen.Device.route)
                    }
                )
            }
            composable(route = Screen.Settings.route) {
                Settings(settingsVM = settingsVM, goBack = navController::popBackStack)
            }
            composable(route = Screen.Create.route) {
                Create(createVM = createVM, goBack = navController::popBackStack )
            }
            composable(route = Screen.Device.route) {
                Device(
                    deviceVM = deviceVM,
                    goCheck = {
                        checkVM.requestCheck(it)
                        navController.navigate(route = Screen.Check.route)
                    },
                    goBack = navController::popBackStack
                )
            }
            composable(route = Screen.Check.route) {
                Check(
                    checkVM = checkVM,
                    viewChangeLog = { navController.navigate(route = Screen.ChangeLog.route) },
                    goBack = {
                        navController.popBackStack()
                        checkVM.cancelCheck()
                    }
                )
            }
            composable(route = Screen.ChangeLog.route) {
                ChangeLog(
                    checkVM = checkVM,
                    goBack = navController::popBackStack
                )
            }
        }
    }
}
