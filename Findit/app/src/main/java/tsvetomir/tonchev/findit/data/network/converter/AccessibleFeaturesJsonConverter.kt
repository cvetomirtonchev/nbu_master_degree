package tsvetomir.tonchev.findit.data.network.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
import tsvetomir.tonchev.findit.utils.parseGenericEnum

class AccessibleFeaturesJsonConverter {
    
    @FromJson
    fun accessibleFeaturesFromJson(
        reader: JsonReader,
        delegate: JsonAdapter<AccessibleFeatures>
    ): AccessibleFeatures =
        parseGenericEnum(reader, delegate, AccessibleFeatures.UNKNOWN)
}