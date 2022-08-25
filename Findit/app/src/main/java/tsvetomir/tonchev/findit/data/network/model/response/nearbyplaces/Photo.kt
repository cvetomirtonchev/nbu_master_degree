package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "height")
    val height: Int?,
    @Json(name = "photoReference")
    val photoReference: String?,
    @Json(name = "width")
    val width: Int?
)