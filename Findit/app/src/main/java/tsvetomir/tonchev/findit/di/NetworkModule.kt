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
import tsvetomir.tonchev.findit.data.network.converter.AccessibleFeaturesJsonConverter
import tsvetomir.tonchev.findit.data.network.service.PlacesGoogleService
import tsvetomir.tonchev.findit.data.network.service.PlacesService
import tsvetomir.tonchev.findit.data.network.service.TrackingService
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
    @Named(MOSHI_PLACES_SERVICE)
    @Singleton
    fun provideMoshiPlaceService(moshiBuilder: Moshi.Builder): Moshi =
        moshiBuilder
            .add(AccessibleFeaturesJsonConverter())
            .build()

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

    @Provides
    @Singleton
    fun providePlacesService(
        @Named(MOSHI_PLACES_SERVICE) moshi: Moshi,
        dataStore: LocalDataStore
    ): PlacesService =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(HttpClient(dataStore).get())
            .build()
            .create(PlacesService::class.java)

    @Provides
    @Singleton
    fun providePlacesGoogleService(
        @Named(MOSHI_DEFAULT) moshi: Moshi,
        dataStore: LocalDataStore
    ): PlacesGoogleService =
        Retrofit.Builder().baseUrl(GOOGLE_MAPS_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(HttpClient(dataStore).get())
            .build()
            .create(PlacesGoogleService::class.java)

    @Provides
    @Singleton
    fun provideTrackingService(
        @Named(MOSHI_DEFAULT) moshi: Moshi,
        dataStore: LocalDataStore
    ): TrackingService =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(HttpClient(dataStore).get())
            .build()
            .create(TrackingService::class.java)


    private const val MOSHI_DEFAULT = "moshiDefault"
    private const val MOSHI_PLACES_SERVICE = "moshiPlaceService"
    private const val GOOGLE_MAPS_URL = "https://maps.googleapis.com/maps/api/"
}