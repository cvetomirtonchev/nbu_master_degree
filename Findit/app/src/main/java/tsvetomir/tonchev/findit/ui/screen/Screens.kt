package tsvetomir.tonchev.findit.ui.screen

sealed class Screens(val route: String) {
    //Login
    object Login : Screens("Login")
    object Register : Screens("Register")
}
