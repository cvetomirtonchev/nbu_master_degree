package tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "business_status")
    val businessStatus: String?,
    @Json(name = "geometry")
    val geometry: Geometry?,
    @Json(name = "")
    val icon: String?,
    @Json(name = "icon_background_color")
    val iconBackgroundColor: String?,
    @Json(name = "icon_mask_base_uri")
    val iconMaskBaseUri: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "opening_hours")
    val openingHours: OpeningHours?,
    @Json(name = "photos")
    val photos: List<Photo>?,
    @Json(name = "place_id")
    val placeId: String?,
    @Json(name = "plus_code")
    val plusCode: PlusCode?,
    @Json(name = "price_level")
    val priceLevel: Int?,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "reference")
    val reference: String?,
    @Json(name = "scope")
    val scope: String?,
    @Json(name = "types")
    val types: List<String>?,
    @Json(name = "user_ratings_total")
    val userRatingsTotal: Int?,
    @Json(name = "vicinity")
    val vicinity: String?
)