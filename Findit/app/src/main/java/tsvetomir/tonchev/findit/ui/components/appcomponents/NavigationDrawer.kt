@file:OptIn(ExperimentalUnitApi::class)

package tsvetomir.tonchev.findit.ui.components.appcomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.ui.theme.FindItTheme


private val screens = listOf(
    "Profile",
    "Saved places",
    "Liked"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DrawerHeader()
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(12.dp))
        NavigationDrawerItems(navController, drawerState)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@ExperimentalUnitApi
@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tsvetomir Tonchev",
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "tsvetomir@gmail.com",
            fontSize = TextUnit(14F, TextUnitType.Sp),
            color = Color.Gray
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerItems(navController: NavHostController, drawerState: DrawerState) {

    val scope = rememberCoroutineScope()

    val currentBackStackEntryAsState = navController.currentBackStackEntryAsState()

    val destination = currentBackStackEntryAsState.value?.destination


    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        label = { Text(text = "Home", style = MaterialTheme.typography.labelMedium) },
        selected = destination?.route == "HomePage",
        onClick = {
            navController.navigate("HomePage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }

        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
    Spacer(modifier = Modifier.height(10.dp))
    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Place, "About") },
        label = {
            Text(
                text = "About",
                style = MaterialTheme.typography.labelMedium
            )
        },
        selected = destination?.route == "AboutPage",
        onClick = {
            navController.navigate("AboutPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
    Spacer(modifier = Modifier.height(10.dp))


    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Settings, "Setting") },
        label = { Text(text = "Setting", style = MaterialTheme.typography.labelMedium) },
        selected = destination?.route == "SettingPage",
        onClick = {
            navController.navigate("SettingPage", navOptions {
                this.launchSingleTop = true
                this.restoreState = true
            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun NavigationDrawerContentPreview() {
    FindItTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        DrawerContent(navController, drawerState)
    }
}