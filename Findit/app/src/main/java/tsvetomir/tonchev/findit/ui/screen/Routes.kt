package tsvetomir.tonchev.findit.ui.screen

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Register : Routes("Register")
}
