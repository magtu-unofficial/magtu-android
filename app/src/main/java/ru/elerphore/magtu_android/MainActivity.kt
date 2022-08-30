package ru.elerphore.magtu_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import ru.elerphore.magtu_android.data.Settings
import ru.elerphore.magtu_android.http_client.WebClient

val wc = WebClient()



val paddingModifier  = Modifier
    .padding(10.dp)
    .fillMaxWidth()
    .clickable {
        CoroutineScope(IO).launch {
            val items = wc.getSchoolDays()
            println("ITEMS: $items")
        }
    }

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        CoroutineScope(IO).launch {

            val db = Room.databaseBuilder(
                applicationContext,
                Database::class.java, "magtu"
            ).build()

            val settingsDao = db.settingsDao()
            val list = settingsDao.getAll()

            if(list.isEmpty()) {
                settingsDao.insert(Settings(0, "ИСп-19-4", false))
            }

            println("LIST DATABASE: $list")
        }

        setContent {
            Main()
        }

    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun MainScreen() {
    Column() {
        Text("Main Screen")
    }
}

@Composable
fun SettingScreen() {
    Column() {
        var textOne by remember { mutableStateOf("Hello") }
        var textTwo by remember { mutableStateOf("Hello") }

        TextField(
            value = textOne,
            onValueChange = { textOne = it },
            label = { Text("Label") }
        )
        TextField(
            value = textTwo,
            onValueChange = { textTwo = it },
            label = { Text("Label") }
        )
        Button(onClick = {

        }) {
            Text(text = "Сохранить")
        }
    }
}

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String) {

    object Home : BottomNavItem("Расписание", R.drawable.ic_launcher_background,"home")
    object MyNetwork: BottomNavItem("Настройки",R.drawable.ic_launcher_background,"my_network")
}

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

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    Main()
}

@Composable
fun Greeting() {
    Column() {
        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }

        Row() {
            Card(elevation = 10.dp, modifier = paddingModifier) {
                Text(text = "Simple Card with elevation",
                    modifier = paddingModifier)
            }
        }
    }
}