package tsvetomir.tonchev.findit.ui.explore

data class ExploreModel(
    val title: String,
    val image: Int,
    val placeModels: List<PlaceModel>
)

data class PlaceModel(
    val title: String,
    val icon: Int,
    val placeType: PlaceType
)

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