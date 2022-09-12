package tsvetomir.tonchev.findit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tsvetomir.tonchev.findit.data.database.AppDatabase
import tsvetomir.tonchev.findit.data.database.dao.SearchDao

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideSearchDao(appDatabase: AppDatabase): SearchDao {
        return appDatabase.searchDao()
    }
}