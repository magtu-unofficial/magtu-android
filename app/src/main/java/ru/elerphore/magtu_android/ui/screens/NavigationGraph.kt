package ru.elerphore.magtu_android.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.elerphore.magtu_android.R
import ru.elerphore.magtu_android.ui.screens.main_screen.MainScreen
import ru.elerphore.magtu_android.ui.screens.setting_screen.SettingScreen

object NavigationGraph {
    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
            composable(BottomNavItem.Home.screen_route) {
                MainScreen()
            }
            composable(BottomNavItem.MyNetwork.screen_route) {
                SettingScreen()
            }
        }
    }

    sealed class BottomNavItem(var title:String, var icon: ImageVector, var screen_route:String) {
        object Home : BottomNavItem("Расписание", Icons.Filled.Home,"home")
        object MyNetwork: BottomNavItem("Настройки",Icons.Filled.Settings,"my_network")
    }
}