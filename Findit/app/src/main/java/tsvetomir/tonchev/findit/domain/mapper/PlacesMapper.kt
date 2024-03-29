package tsvetomir.tonchev.findit.domain.mapper

import tsvetomir.tonchev.findit.data.network.model.response.PlacesResponse
import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.NearbyPlacesResponse
import tsvetomir.tonchev.findit.domain.model.AccessibleFeaturesUiModel
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.utils.accessibleFeatureToImageVector
import tsvetomir.tonchev.findit.utils.accessibleFeatureToRes
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
                place.accessibleFeaturesUiModelList = placeResponse.accessibleFeatures.map {
                    AccessibleFeaturesUiModel(
                        accessibleFeaturesResIds = accessibleFeatureToRes(it),
                        accessibleFeatureImageVector = accessibleFeatureToImageVector(it)
                    )
                }
            }
            place
        }
}

