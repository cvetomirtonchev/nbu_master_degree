package tsvetomir.tonchev.findit.domain.repository

import android.location.Location
import retrofit2.Response
import tsvetomir.tonchev.findit.BuildConfig
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.data.database.AppDatabase
import tsvetomir.tonchev.findit.data.database.entity.SearchHistoryEntity
import tsvetomir.tonchev.findit.data.network.model.request.AddPlaceRequest
import tsvetomir.tonchev.findit.data.network.model.request.SearchEventRequest
import tsvetomir.tonchev.findit.data.network.service.PlacesGoogleService
import tsvetomir.tonchev.findit.data.network.service.PlacesService
import tsvetomir.tonchev.findit.data.network.service.TrackingService
import tsvetomir.tonchev.findit.domain.mapper.PlacesMapper
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
import tsvetomir.tonchev.findit.domain.model.LastSearchModelCache
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.ui.explore.ExploreModel
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.ui.explore.PlaceType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRepository @Inject constructor(
    private val placesGoogleService: PlacesGoogleService,
    private val placesMapper: PlacesMapper,
    private val database: AppDatabase,
    private val placesService: PlacesService,
    private val trackingService: TrackingService
) {

    private var lastSearchModel: LastSearchModelCache? = null

    suspend fun addAccessiblePlace(placeUiModel: PlaceUiModel): Response<Void> =
        placesService.addAccessiblePlace(
            AddPlaceRequest(
                placeId = placeUiModel.id,
                lat = placeUiModel.lat,
                lng = placeUiModel.lng,
                name = placeUiModel.name,
                rating = placeUiModel.rating,
                address = placeUiModel.address,
                city = placeUiModel.cityName,
                placeType = placeUiModel.placeType,
                accessibleFeatures = listOf(
                    AccessibleFeatures.ENTRANCE,
                    AccessibleFeatures.RESTROOM,
                    AccessibleFeatures.PARKING,
                    AccessibleFeatures.SEATING
                )
            )
        )

    suspend fun findAllPlaces(
        searchType: String,
        location: Location,
        cityName: String
    ): List<PlaceUiModel> {
        saveLastSearch(searchType, cityName)
        trackSearch(searchType, cityName)

        if (lastSearchModel != null && isLastSearchMatched(
                searchType,
                location
            )
        ) {
            lastSearchModel?.let {
                return it.result
            }
        }

        val placesGoogleApi = findAllPlacesFromGoogle(searchType, location, cityName)
        val placesLocalApi = placesService.findNearbyPlaces(cityName, searchType)
        val places = placesMapper.mapAccessiblePlaces(placesGoogleApi, placesLocalApi)

        lastSearchModel =
            LastSearchModelCache(searchType, location, places)
        return places
    }

    fun getSearchHistory(): List<SearchHistoryEntity> = database.searchDao().getSearchHistory()

    fun clearSearchHistory() {
        database.searchDao().delete()
    }

    private suspend fun trackSearch(searchType: String, cityName: String): Response<Void> =
        trackingService.trackSearchEvent(SearchEventRequest(searchType, cityName))

    private suspend fun findAllPlacesFromGoogle(
        searchType: String,
        location: Location,
        cityName: String,
    ): List<PlaceUiModel> {
        val queryMap = mapOf(
            LOCATION to "${location.latitude}%2C${location.longitude}",
            RADIUS to "1500",
            TYPE to searchType,
            KEY to BuildConfig.MAPS_KEY
        )
        return placesMapper.mapNearbyPlacesResponse(
            placesGoogleService.findNearbyPlaces(queryMap),
            cityName,
            searchType
        )
    }

    private fun saveLastSearch(searchType: String, cityName: String) {
        val allSearches = database.searchDao().getSearchHistory()
        if (allSearches.size >= 10) {
            database.searchDao().deleteSearchHistory(allSearches[0])
        }
        database.searchDao()
            .insertSearchHistory(SearchHistoryEntity(searchType = searchType, city = cityName))
    }

    private fun isLastSearchMatched(
        searchType: String,
        location: Location,
    ): Boolean =
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
                titleResId = R.string.food_and_drink,
                image = R.drawable.photo_food,
                placeModels = listOf(
                    PlaceModel(
                        titleResId = R.string.cafes,
                        icon = R.drawable.ic_coffee,
                        placeType = PlaceType.CAFES
                    ),
                    PlaceModel(
                        titleResId = R.string.bars,
                        icon = R.drawable.ic_local_bar,
                        placeType = PlaceType.BAR
                    ),
                    PlaceModel(
                        titleResId = R.string.restaurants,
                        icon = R.drawable.ic_restaurant,
                        placeType = PlaceType.RESTAURANT
                    )
                )
            )

        private fun generateMoneyTab(): ExploreModel =
            ExploreModel(
                titleResId = R.string.money,
                image = R.drawable.cash_card,
                placeModels = listOf(
                    PlaceModel(
                        titleResId = R.string.atm,
                        icon = R.drawable.ic_atm,
                        placeType = PlaceType.ATM
                    ),
                    PlaceModel(
                        titleResId = R.string.bank,
                        icon = R.drawable.ic_bank,
                        placeType = PlaceType.BANK
                    )
                )
            )

        private fun generateShoppingTab(): ExploreModel =
            ExploreModel(
                titleResId = R.string.shopping,
                image = R.drawable.shopping,
                placeModels = listOf(
                    PlaceModel(
                        titleResId = R.string.shopping_malls,
                        icon = R.drawable.ic_mall,
                        placeType = PlaceType.SHOPPING_MALL
                    ),
                    PlaceModel(
                        titleResId = R.string.grocery_stores,
                        icon = R.drawable.ic_store,
                        placeType = PlaceType.GROCERY_STORE
                    )
                )
            )

        private fun generateHotelTab(): ExploreModel =
            ExploreModel(
                titleResId = R.string.stay,
                image = R.drawable.hotel,
                placeModels = listOf(
                    PlaceModel(
                        titleResId = R.string.hotels,
                        icon = R.drawable.ic_hotel,
                        placeType = PlaceType.HOTEL
                    )
                )
            )
    }
}