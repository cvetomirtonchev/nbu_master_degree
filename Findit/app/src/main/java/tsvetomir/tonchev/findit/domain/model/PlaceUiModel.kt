package tsvetomir.tonchev.findit.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import tsvetomir.tonchev.findit.data.network.model.response.nearbyplaces.Photo

data class PlaceUiModel(
    val id: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val photos: List<Photo>,
    val rating: Double,
    val address: String,
    var isAccessible: Boolean,
    val cityName: String,
    val placeType: String,
    var accessibleFeaturesUiModelList: List<AccessibleFeaturesUiModel> = emptyList()
)

data class AccessibleFeaturesUiModel(
    @StringRes
    val accessibleFeaturesResIds: Int,
    val accessibleFeatureImageVector: ImageVector
)