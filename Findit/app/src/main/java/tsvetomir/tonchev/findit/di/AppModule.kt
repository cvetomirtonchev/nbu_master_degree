package tsvetomir.tonchev.findit.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun provideDispatchersProvider(): CoroutineDispatchersProvider =
        object : CoroutineDispatchersProvider {
            override val default = Dispatchers.Default
            override val io = Dispatchers.IO
            override val main = Dispatchers.Main
            override val unconfined = Dispatchers.Unconfined
        }
}