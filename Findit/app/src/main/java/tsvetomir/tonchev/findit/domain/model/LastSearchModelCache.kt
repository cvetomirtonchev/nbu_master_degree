package tsvetomir.tonchev.findit.domain.model

import android.location.Location

data class LastSearchModelCache(
    val isAccessibleEnabled: Boolean,
    val searchType: String,
    val location: Location,
    val result: List<PlaceUiModel>
)
