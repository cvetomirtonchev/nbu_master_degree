package tsvetomir.tonchev.findit.domain.mapper

import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.data.network.model.response.PlacesResponse
import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.NearbyPlacesResponse
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import javax.inject.Inject

class PlacesMapper @Inject constructor() {

    fun mapNearbyPlacesResponse(
        nearbyPlacesResponse: NearbyPlacesResponse,
        cityName: String,
        placeType: String
    ): List<PlaceUiModel> =
        nearbyPlacesResponse.results?.map {
            PlaceUiModel(
                id = it.placeId ?: "",
                lat = it.geometry?.location?.lat ?: 0.0,
                lng = it.geometry?.location?.lng ?: 0.0,
                name = it.name ?: "",
                photos = it.photos ?: emptyList(),
                rating = it.rating ?: 0.0,
                address = it.vicinity ?: "",
                isAccessible = false,
                cityName = cityName,
                placeType = placeType
            )
        } ?: emptyList()

    fun mapAccessiblePlaces(
        placesGoogleApi: List<PlaceUiModel>,
        placesLocalApi: List<PlacesResponse>
    ): List<PlaceUiModel> =
        placesGoogleApi.map { place ->
            val placeResponse: PlacesResponse? =
                placesLocalApi.find { localPlace -> localPlace.placeId == place.id }
            if (placeResponse != null) {
                place.isAccessible = true
                place.accessibleFeaturesResIds = placeResponse.accessibleFeatures.map {
                    accessibleFeatureToRes(it)
                }
            }
            place
        }

    private fun accessibleFeatureToRes(accessibleFeature: AccessibleFeatures): Int =
        when (accessibleFeature) {
            AccessibleFeatures.ENTRANCE -> R.string.entrance
            AccessibleFeatures.SEATING -> R.string.seating
            AccessibleFeatures.PARKING -> R.string.parking
            AccessibleFeatures.RESTROOM -> R.string.restroom
            AccessibleFeatures.UNKNOWN -> 0
        }
}

