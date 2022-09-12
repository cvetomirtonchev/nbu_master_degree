package tsvetomir.tonchev.findit.data.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchEventRequest(
    @Json(name = "placeType")
    val placeType: String,
    @Json(name = "city")
    val city: String
)