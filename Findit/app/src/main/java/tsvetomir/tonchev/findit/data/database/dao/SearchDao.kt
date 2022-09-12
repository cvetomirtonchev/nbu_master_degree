package tsvetomir.tonchev.findit.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tsvetomir.tonchev.findit.data.database.entity.SearchHistoryEntity

@Dao
interface SearchDao {
    @Query("SELECT * FROM searchhistoryentity")
    fun getSearchHistory(): List<SearchHistoryEntity>

    @Insert
    fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Delete
    fun deleteSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Query("DELETE FROM searchhistoryentity")
    fun delete()
}