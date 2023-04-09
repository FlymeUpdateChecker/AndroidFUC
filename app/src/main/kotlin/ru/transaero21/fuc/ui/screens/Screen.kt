package ru.transaero21.fuc.ui.screens

sealed class Screen(val route: String) {
    object Devices : Screen("devices_screen")
    object Create : Screen("create_screen")
    object Settings : Screen("settings_screen")
    object CheckOut : Screen("check_screen")
    object ChangeLog : Screen("change_log_screen")
}