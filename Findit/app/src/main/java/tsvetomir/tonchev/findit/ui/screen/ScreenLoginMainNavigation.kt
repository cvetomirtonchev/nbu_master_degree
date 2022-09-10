package tsvetomir.tonchev.findit.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tsvetomir.tonchev.findit.ui.components.CircularIndeterminateProgressBar
import tsvetomir.tonchev.findit.ui.login.LoginViewModel
import tsvetomir.tonchev.findit.ui.register.RegisterScreen

@ExperimentalComposeUiApi
@Composable
fun FindItNavHost(loginViewModel: LoginViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Login.route) {

        composable(Screens.Login.route) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(Screens.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
    CircularIndeterminateProgressBar(loginViewModel)
}
