package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NearbyPlacesResponse(
    @Json(name = "results")
    val results: List<Result>?,
    @Json(name = "status")
    val status: String?
)