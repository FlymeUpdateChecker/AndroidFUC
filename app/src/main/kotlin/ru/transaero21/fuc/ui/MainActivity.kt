package ru.transaero21.fuc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.transaero21.fuc.ui.screens.Screen
import ru.transaero21.fuc.ui.screens.devices.DevicesScreen
import ru.transaero21.fuc.ui.screens.settings.Settings
import ru.transaero21.fuc.ui.theme.FUCTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FUCTheme {
                MainNav()
            }
        }
    }
}

@Composable
private fun MainNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.Devices.route ) {
        composable(
            route = Screen.Devices.route
        ) {
            var isHead by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                isHead = navController.previousBackStackEntry != null
            }
            DevicesScreen(
                goBack = if (isHead) { { navController.popBackStack() } } else null,
                addDevice = { navController.navigate(route = Screen.Create.route) },
                openSettings = { navController.navigate(route = Screen.Settings.route) }
            )
        }
        composable(
            route = Screen.Settings.route
        ) {
            Settings { navController.popBackStack() }
        }
    }
}