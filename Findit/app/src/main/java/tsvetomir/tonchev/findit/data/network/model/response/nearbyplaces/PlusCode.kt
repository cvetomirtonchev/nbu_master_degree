package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlusCode(
    @Json(name = "compound_code")
    val compoundCode: String?,
    @Json(name = "global_code")
    val globalCode: String?
)