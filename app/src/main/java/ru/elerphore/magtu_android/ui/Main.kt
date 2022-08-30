package ru.elerphore.magtu_android.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import ru.elerphore.magtu_android.ui.screens.NavigationGraph

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation.BottomNavigation(navController = navController) },
        contentColor = Color.Blue
    ) {
        NavigationGraph.NavigationGraph(navController = navController)
    }
}