package tsvetomir.tonchev.findit.ui.screen

sealed class Screens(val route: String) {
    //Login
    object Login : Screens("Login")
    object Register : Screens("Register")

    //Dashboard
    object Explore : Screens("Explore")
    object Search : Screens("Search")
    object Liked : Screens("Liked")
    object Profile : Screens("Profile")

    //Places
    object PlacesMap : Screens("PlacesMap")
    object PlacesList : Screens("PlacesList")
}
