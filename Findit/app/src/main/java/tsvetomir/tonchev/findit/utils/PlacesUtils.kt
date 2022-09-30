package tsvetomir.tonchev.findit.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
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

fun fromStringToPlaceType(placeType: String): PlaceType =
    when (placeType) {
        "cafe" -> PlaceType.CAFES
        "restaurant" -> PlaceType.RESTAURANT
        "bar" -> PlaceType.BAR
        "atm" -> PlaceType.ATM
        "bank" -> PlaceType.BANK
        "hotel" -> PlaceType.HOTEL
        "shopping_mall" -> PlaceType.SHOPPING_MALL
        "grocery_store" -> PlaceType.GROCERY_STORE
        else -> PlaceType.CAFES
    }

fun fromPlaceTypeToIconRes(placeType: PlaceType): Int =
    when (placeType) {
        PlaceType.HOTEL -> R.drawable.ic_hotel
        PlaceType.ATM -> R.drawable.ic_atm
        PlaceType.RESTAURANT -> R.drawable.ic_restaurant
        PlaceType.BAR -> R.drawable.ic_local_bar
        PlaceType.CAFES -> R.drawable.ic_coffee
        PlaceType.BANK -> R.drawable.ic_bank
        PlaceType.SHOPPING_MALL -> R.drawable.ic_mall
        PlaceType.GROCERY_STORE -> R.drawable.ic_store
    }

fun openDirectionsInMaps(context: Context, lat: Double, lng: Double) {
    val gmmIntentUri =
        Uri.parse("google.navigation:q=$lat,$lng")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}

fun accessibleFeatureToRes(accessibleFeature: AccessibleFeatures): Int =
    when (accessibleFeature) {
        AccessibleFeatures.ENTRANCE -> R.string.entrance
        AccessibleFeatures.SEATING -> R.string.seating
        AccessibleFeatures.PARKING -> R.string.parking
        AccessibleFeatures.RESTROOM -> R.string.restroom
        AccessibleFeatures.UNKNOWN -> 0
    }