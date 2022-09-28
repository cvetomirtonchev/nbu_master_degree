package tsvetomir.tonchev.findit.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader

fun <T> parseGenericEnum(
    reader: JsonReader,
    delegate: JsonAdapter<T>,
    unknown: T
): T =
    try {
        val value = reader.nextString()
        if (value.isEmpty()) {
            unknown
        } else {
            delegate.fromJsonValue(value) ?: unknown
        }
    } catch (e: JsonDataException) {
        unknown
    }