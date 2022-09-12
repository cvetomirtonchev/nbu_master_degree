package tsvetomir.tonchev.findit.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "search_type")
    val searchType: String,

    @ColumnInfo(name = "city")
    val city: String
)