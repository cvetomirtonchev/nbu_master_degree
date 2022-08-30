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
    var forDisability: Boolean,
    val cityName: String
)