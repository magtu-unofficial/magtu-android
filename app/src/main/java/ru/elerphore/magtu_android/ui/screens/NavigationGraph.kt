package ru.elerphore.magtu_android.ui.screens

import androidx.compose.runtime.Composable
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

    sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String) {
        object Home : BottomNavItem("Расписание", R.drawable.ic_launcher_background,"home")
        object MyNetwork: BottomNavItem("Настройки",R.drawable.ic_launcher_background,"my_network")
    }
}