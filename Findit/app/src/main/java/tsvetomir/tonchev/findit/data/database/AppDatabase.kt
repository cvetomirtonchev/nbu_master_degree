package tsvetomir.tonchev.findit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tsvetomir.tonchev.findit.data.database.dao.SearchDao
import tsvetomir.tonchev.findit.data.database.entity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}