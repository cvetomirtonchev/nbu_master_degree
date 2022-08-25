package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import tsvetomir.tonchev.findit.ui.places.list.PlacesList
import tsvetomir.tonchev.findit.ui.places.map.MapScreen
import tsvetomir.tonchev.findit.ui.screen.Screens


@ExperimentalMaterial3Api
@Composable
fun PlacesNavigation(viewModel: PlacesViewModel, location: Location) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopNavigation(navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Screens.PlacesMap.route) {
                composable(Screens.PlacesMap.route) {
                    MapScreen(location, viewModel)
                }
                composable(Screens.PlacesList.route) {
                    PlacesList(viewModel)
                }
            }
        }
    }
}

@Composable
fun TopNavigation(navController: NavHostController) {
    val tabItems = listOf("PlacesMap", "PlacesList")
    val selectedItem = remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        tabItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedItem.value = index
                    navController.navigate(item, navOptions {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    })

                },
                icon = {
                    when (item) {
                        "PlacesMap" -> Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = setSelectedColor(selectedItem.value, index)
                        )
                        "PlacesList" -> Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = setSelectedColor(selectedItem.value, index)
                        )
                    }
                },
                label = {
                    Text(
                        text = item,
                        color = setSelectedColor(selectedItem.value, index)
                    )
                })
        }
    }
}

private fun setSelectedColor(selectedValue: Int, currentIndex: Int): Color =
    if (selectedValue == currentIndex) {
        Color.White
    } else {
        Color.Gray
    }