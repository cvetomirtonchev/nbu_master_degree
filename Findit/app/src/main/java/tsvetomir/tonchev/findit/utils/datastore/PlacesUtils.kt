package tsvetomir.tonchev.findit.utils.datastore

import android.content.Context
import android.content.Intent
import android.net.Uri
import tsvetomir.tonchev.findit.ui.explore.PlaceType

fun fromPlaceTypeToString(placeType: PlaceType): String =
    when (placeType) {
        PlaceType.HOTEL -> "hotel"
        PlaceType.ATM -> "atm"
        PlaceType.RESTAURANT -> "restaurant"
        PlaceType.BAR -> "bar"
        PlaceType.CAFES -> "cafe"
        PlaceType.BANK -> "bank"
        PlaceType.SHOPPING_MALL -> "shopping_mall"
        PlaceType.GROCERY_STORE -> "store"
    }

fun openDirectionsInMaps(context: Context, lat: Double, lng: Double) {
    val gmmIntentUri =
        Uri.parse("google.navigation:q=$lat,$lng")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}