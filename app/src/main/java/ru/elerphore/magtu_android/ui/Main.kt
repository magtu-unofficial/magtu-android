package ru.elerphore.magtu_android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.rememberNavController
import ru.elerphore.magtu_android.ui.screens.NavigationGraph

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigation.BottomNavigation(navController = navController) },
        contentColor = Color.Blue
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph.NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = {
        Text(
            color = Color.White,
            text = "ИСп-19-2",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    },
        backgroundColor = Color.Blue
    )
}