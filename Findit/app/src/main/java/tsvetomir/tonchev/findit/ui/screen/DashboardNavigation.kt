package tsvetomir.tonchev.findit.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.ui.components.appcomponents.AppBar
import tsvetomir.tonchev.findit.ui.components.appcomponents.DrawerContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = { DrawerContent(navController, drawerState) },
        drawerState = drawerState
    ) {
        Scaffold(topBar = {
            AppBar {
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
            BottomAppBar {
              
            }

        }) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = "Explore") {
                    composable("Explore") {

                    }
                    composable("Search") {

                    }
                    composable("Analytics") {

                    }
                    composable("Liked") {

                    }
                }
            }
        }

    }
}