package tsvetomir.tonchev.findit.domain.repository

import android.location.Location
import tsvetomir.tonchev.findit.BuildConfig
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.data.network.service.PlacesService
import tsvetomir.tonchev.findit.domain.mapper.PlacesMapper
import tsvetomir.tonchev.findit.domain.model.LastSearchModelCache
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.ui.explore.ExploreModel
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.ui.explore.PlaceType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRepository @Inject constructor(
    private val placesService: PlacesService,
    private val placesMapper: PlacesMapper
) {

    private var lastSearchModel: LastSearchModelCache? = null

    suspend fun findAllPlaces(searchType: String, location: Location): List<PlaceUiModel> {
        if (lastSearchModel != null && isLastSearchMatched(searchType, location)) {
            lastSearchModel?.let {
                return it.result
            }
        }
        val queryMap = mapOf(
            LOCATION to "${location.latitude}%2C${location.longitude}",
            RADIUS to "1500",
            TYPE to searchType,
            KEY to BuildConfig.MAPS_KEY
        )
        val mappedResponse =
            placesMapper.mapNearbyPlacesResponse(placesService.findNearbyPlaces(queryMap))
        lastSearchModel = LastSearchModelCache(searchType, location, mappedResponse)
        return mappedResponse
    }

    private fun isLastSearchMatched(searchType: String, location: Location): Boolean =
        searchType == lastSearchModel?.searchType
                && location.latitude == lastSearchModel?.location?.latitude
                && location.longitude == lastSearchModel?.location?.longitude


    companion object {
        private const val LOCATION = "location"
        private const val RADIUS = "radius"
        private const val TYPE = "type"
        private const val KEY = "key"

        fun generatePlaces(): List<ExploreModel> =
            listOf(
                generateFoodAndDrinkTab(),
                generateMoneyTab(),
                generateShoppingTab(),
                generateHotelTab()
            )

        private fun generateFoodAndDrinkTab(): ExploreModel =
            ExploreModel(
                title = "Food and Drink",
                image = R.drawable.photo_food,
                placeModels = listOf(
                    PlaceModel(
                        title = "Cafes",
                        icon = R.drawable.ic_coffee,
                        placeType = PlaceType.CAFES
                    ),
                    PlaceModel(
                        title = "Bars",
                        icon = R.drawable.ic_local_bar,
                        placeType = PlaceType.BAR
                    ),
                    PlaceModel(
                        title = "Restaurants",
                        icon = R.drawable.ic_restaurant,
                        placeType = PlaceType.RESTAURANT
                    )
                )
            )

        private fun generateMoneyTab(): ExploreModel =
            ExploreModel(
                title = "Money",
                image = R.drawable.cash_card,
                placeModels = listOf(
                    PlaceModel(
                        title = "ATMs",
                        icon = R.drawable.ic_atm,
                        placeType = PlaceType.ATM
                    ),
                    PlaceModel(
                        title = "Banks",
                        icon = R.drawable.ic_bank,
                        placeType = PlaceType.BANK
                    )
                )
            )

        private fun generateShoppingTab(): ExploreModel =
            ExploreModel(
                title = "Shopping",
                image = R.drawable.shopping,
                placeModels = listOf(
                    PlaceModel(
                        title = "Shopping Malls",
                        icon = R.drawable.ic_mall,
                        placeType = PlaceType.SHOPPING_MALL
                    ),
                    PlaceModel(
                        title = "Grocery Stores",
                        icon = R.drawable.ic_store,
                        placeType = PlaceType.GROCERY_STORE
                    )
                )
            )

        private fun generateHotelTab(): ExploreModel =
            ExploreModel(
                title = "Stay",
                image = R.drawable.hotel,
                placeModels = listOf(
                    PlaceModel(
                        title = "Hotels",
                        icon = R.drawable.ic_hotel,
                        placeType = PlaceType.HOTEL
                    )
                )
            )
    }
}