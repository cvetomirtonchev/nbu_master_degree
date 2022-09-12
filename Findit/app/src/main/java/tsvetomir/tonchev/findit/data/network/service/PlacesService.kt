package tsvetomir.tonchev.findit.data.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import tsvetomir.tonchev.findit.data.network.model.request.AddPlaceRequest
import tsvetomir.tonchev.findit.data.network.model.response.PlacesResponse

interface PlacesService {
    @GET("api/places/all")
    suspend fun findNearbyPlaces(
        @Query("city") city: String,
        @Query("placeType") placeType: String
    ): List<PlacesResponse>

    @PUT("api/places/place")
    suspend fun addAccessiblePlace(@Body addPlaceRequest: AddPlaceRequest): Response<Void>
}