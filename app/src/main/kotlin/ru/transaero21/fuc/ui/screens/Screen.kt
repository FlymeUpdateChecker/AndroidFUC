package ru.transaero21.fuc.ui.screens

sealed class Screen(val route: String) {
    object Create : Screen("create_screen")
    object Devices : Screen("devices_screen")
    object Device : Screen("device_screen")
    object Check : Screen("check_screen")
    object ChangeLog : Screen("change_log_screen")
    object Settings : Screen("settings_screen")
}