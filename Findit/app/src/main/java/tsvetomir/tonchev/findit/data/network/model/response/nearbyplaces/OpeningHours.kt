package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpeningHours(
    @Json(name = "open_now")
    val openNow: Boolean?
)