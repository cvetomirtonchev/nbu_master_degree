package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Viewport(
    @Json(name = "northeast")
    val northeast: Northeast?,
    @Json(name = "southwest")
    val southwest: Southwest?
)