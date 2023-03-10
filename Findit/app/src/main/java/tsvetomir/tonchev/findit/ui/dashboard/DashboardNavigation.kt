package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.components.appcomponents.AppBar
import tsvetomir.tonchev.findit.ui.components.appcomponents.DrawerContent
import tsvetomir.tonchev.findit.ui.explore.ExploreScreen
import tsvetomir.tonchev.findit.ui.history.HistoryScreen
import tsvetomir.tonchev.findit.ui.profile.ProfileScreen

private const val TAB_EXPLORE = 0
private const val TAB_HISTORY = 1
private const val TAB_PROFILE = 2

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalUnitApi
@Composable
fun NavigationPage(viewModel: DashboardViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val routeName = navBackStackEntry.value?.destination?.route ?: ""
    val tabItems =
        listOf(
            stringResource(R.string.explore),
            stringResource(R.string.history),
            stringResource(R.string.profile)
        )

    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController, drawerState, viewModel) },
        drawerState = drawerState
    ) {
        Scaffold(topBar = {
            AppBar(title = routeName) {
                if (drawerState.isClosed) {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                } else {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            }
        }, bottomBar = {
            NavigationBar {
                BottomNavigation(navController, tabItems)
            }

        }) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = tabItems[TAB_EXPLORE]) {
                    composable(tabItems[TAB_EXPLORE]) {
                        ExploreScreen()
                    }
                    composable(tabItems[TAB_HISTORY]) {
                        HistoryScreen(viewModel)
                    }
                    composable(tabItems[TAB_PROFILE]) {
                        ProfileScreen(viewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigation(navController: NavHostController, tabItems: List<String>) {
    val selectedItem = rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val parentRouteName = navBackStackEntry.value?.destination?.route

    val bottomBarDestination = tabItems.any { it == parentRouteName }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp))
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
                            stringResource(R.string.explore) -> Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = setSelectedColor(selectedItem.value, index)
                            )
                            stringResource(R.string.history) -> Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = setSelectedColor(selectedItem.value, index)
                            )
                            stringResource(R.string.profile) -> Icon(
                                imageVector = Icons.Default.Person,
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
}

private fun setSelectedColor(selectedValue: Int, currentIndex: Int): Color =
    if (selectedValue == currentIndex) {
        Color.White
    } else {
        Color.Gray
    }