package tsvetomir.tonchev.findit.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tsvetomir.tonchev.findit.data.network.UserService
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
        @Named(MOSHI_DEFAULT) moshi: Moshi
    ): UserService =
        Retrofit.Builder().baseUrl("https://www.google.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UserService::class.java)


    private const
    val MOSHI_DEFAULT = "moshiDefault"
}