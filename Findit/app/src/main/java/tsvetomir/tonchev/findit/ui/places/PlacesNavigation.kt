package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import tsvetomir.tonchev.findit.R
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
    val tabItems = listOf("Places Map", "Places List")
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
                        "Places Map" -> Icon(
                            painter = painterResource(id = R.drawable.ic_public),
                            contentDescription = null,
                            tint = setSelectedColor(selectedItem.value, index)
                        )
                        "Places List" -> Icon(
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