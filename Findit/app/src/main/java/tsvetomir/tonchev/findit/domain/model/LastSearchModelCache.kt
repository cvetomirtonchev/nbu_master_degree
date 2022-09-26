package tsvetomir.tonchev.findit.domain.model

import android.location.Location

data class LastSearchModelCache(
    val searchType: String,
    val location: Location,
    val result: List<PlaceUiModel>
)
