package tsvetomir.tonchev.findit.data.network.service

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.NearbyPlacesResponse

interface PlacesGoogleService {

    @Headers("Content-Type: application/json")
    @GET("place/nearbysearch/json")
    suspend fun findNearbyPlaces(@QueryMap(encoded = true) queryMap: Map<String, String>): NearbyPlacesResponse
}