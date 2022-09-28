package tsvetomir.tonchev.findit.domain.model

import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.Photo

data class PlaceUiModel(
    val id: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val photos: List<Photo>,
    val rating: Double,
    val address: String,
    var isAccessible: Boolean,
    val cityName: String,
    val placeType: String,
    var accessibleFeaturesResIds: List<Int> = emptyList()
)