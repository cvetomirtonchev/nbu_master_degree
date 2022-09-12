package tsvetomir.tonchev.findit.ui.screen

sealed class Screens(val route: String) {
    //Login
    object Login : Screens("Login")
    object Register : Screens("Register")

    //Dashboard
    object Explore : Screens("Explore")
    object History : Screens("History")
    object Profile : Screens("Profile")

    //Places
    object PlacesMap : Screens("Places Map")
    object PlacesList : Screens("Places List")
}
