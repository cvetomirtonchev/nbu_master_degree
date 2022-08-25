package tsvetomir.tonchev.findit.utils.datastore

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