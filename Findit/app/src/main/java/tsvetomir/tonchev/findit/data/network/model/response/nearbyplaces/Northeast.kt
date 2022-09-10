package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Northeast(
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lng")
    val lng: Double?
)