package tsvetomir.tonchev.findit.domain.mapper

import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.NearbyPlacesResponse
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import javax.inject.Inject

class PlacesMapper @Inject constructor() {

    fun mapNearbyPlacesResponse(nearbyPlacesResponse: NearbyPlacesResponse): List<PlaceUiModel> =
        nearbyPlacesResponse.results?.map {
            PlaceUiModel(
                id = it.placeId ?: "",
                lat = it.geometry?.location?.lat ?: 0.0,
                lng = it.geometry?.location?.lng ?: 0.0,
                name = it.name ?: "",
                photos = it.photos ?: emptyList(),
                rating = it.rating ?: 0.0,
                address = it.vicinity ?: "",
                forDisability = false
            )
        } ?: emptyList()
}