package tsvetomir.tonchev.findit.data.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlacesResponse(
    @Json(name = "placeId")
    val placeId: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "address")
    val address: String,
    @Json(name = "city")
    val city: String
)