package tsvetomir.tonchev.findit.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tsvetomir.tonchev.findit.BuildConfig
import tsvetomir.tonchev.findit.data.network.clieant.HttpClient
import tsvetomir.tonchev.findit.data.network.service.UserService
import tsvetomir.tonchev.findit.utils.datastore.LocalDataStore
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshiBuilder(): Moshi.Builder =
        Moshi.Builder()

    @Provides
    @Named(MOSHI_DEFAULT)
    @Singleton
    fun provideMoshi(moshiBuilder: Moshi.Builder): Moshi =
        moshiBuilder.build()

    @Provides
    @Singleton
    fun provideUserService(
        @Named(MOSHI_DEFAULT) moshi: Moshi,
        dataStore: LocalDataStore
    ): UserService =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(HttpClient(dataStore).get())
            .build()
            .create(UserService::class.java)


    private const val MOSHI_DEFAULT = "moshiDefault"
}