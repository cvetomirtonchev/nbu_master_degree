package tsvetomir.tonchev.findit.ui.components.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
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
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.data.network.model.response.User
import tsvetomir.tonchev.findit.ui.dashboard.DashboardViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.SecondaryGreenAlpha


private const val HELP = "Help"
private const val LOG_OUT = "Log out"
private val navigationMenus = listOf(
    HELP,
    LOG_OUT
)

@ExperimentalUnitApi
@ExperimentalMaterial3Api
@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val user: User? = viewModel.userMutableState.value
    Column(
        modifier = Modifier
            .background(color = SecondaryGreenAlpha)
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let {
            DrawerHeader(it)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(12.dp))
            NavigationDrawerItems(navController, drawerState, viewModel)
            Spacer(modifier = Modifier.weight(1f))
        } ?: run {
            Text(
                text = "Place log in",
                fontSize = TextUnit(18F, TextUnitType.Sp),
                color = Color.White
            )
        }
    }
}

@ExperimentalUnitApi
@Composable
fun DrawerHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${user.firstName} ${user.lastName}",
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = user.email,
            fontSize = TextUnit(14F, TextUnitType.Sp),
            color = Color.Gray
        )
    }

}


@ExperimentalMaterial3Api
@Composable
fun NavigationDrawerItems(
    navController: NavHostController,
    drawerState: DrawerState,
    viewModel: DashboardViewModel
) {

    val scope = rememberCoroutineScope()

    val currentBackStackEntryAsState = navController.currentBackStackEntryAsState()

    val destination = currentBackStackEntryAsState.value?.destination

    navigationMenus.forEachIndexed { index, item ->
        run {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = navigationMenus[index],
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                selected = destination?.route == navigationMenus[index],
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    if (item == LOG_OUT) {
                        viewModel.logout()
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                icon = {
                    when (item) {
                        HELP -> Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White
                        )
                        LOG_OUT -> Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                },
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@ExperimentalUnitApi
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