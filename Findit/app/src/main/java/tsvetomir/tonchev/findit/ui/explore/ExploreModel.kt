package tsvetomir.tonchev.findit.ui.explore

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

data class ExploreModel(
    @StringRes val titleResId: Int,
    @DrawableRes val image: Int,
    val placeModels: List<PlaceModel>
)

@Parcelize
data class PlaceModel(
    @StringRes val titleResId: Int,
    @DrawableRes val icon: Int,
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