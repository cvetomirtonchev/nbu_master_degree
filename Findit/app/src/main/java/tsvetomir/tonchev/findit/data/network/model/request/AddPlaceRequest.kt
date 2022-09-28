package tsvetomir.tonchev.findit.data.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures

@JsonClass(generateAdapter = true)
class AddPlaceRequest(
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
    val city: String,
    @Json(name = "placeType")
    val placeType: String,
    @Json(name = "accessibleFeatures")
    val accessibleFeatures: List<AccessibleFeatures>
)