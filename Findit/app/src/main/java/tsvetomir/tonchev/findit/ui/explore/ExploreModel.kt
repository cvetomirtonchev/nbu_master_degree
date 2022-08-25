package tsvetomir.tonchev.findit.ui.explore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ExploreModel(
    val title: String,
    val image: Int,
    val placeModels: List<PlaceModel>
)

@Parcelize
data class PlaceModel(
    val title: String,
    val icon: Int,
    val placeType: PlaceType
) : Parcelable

enum class PlaceType {
    RESTAURANT,
    BAR,
    CAFES,
    ATM,
    BANK,
    HOTEL,
    SHOPPING_MALL,
    GROCERY_STORE
}