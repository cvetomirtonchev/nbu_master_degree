package tsvetomir.tonchev.findit.data.network.clieant

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import tsvetomir.tonchev.findit.BuildConfig
import tsvetomir.tonchev.findit.utils.datastore.LocalDataStore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HttpClient @Inject constructor(private val dataStore: LocalDataStore) {

    private val okHttpBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
    }

    fun get(): OkHttpClient {
        okHttpBuilder
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(BuildConfig.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(dataStore))
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })

        return okHttpBuilder.build()
    }

    private class AuthInterceptor(private val dataStore: LocalDataStore) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = runBlocking { dataStore.getSessionToken() }

            if (token.isNotEmpty()) {
                return chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(
                            AUTHORIZATION_HEADER,
                            token
                        )
                        .build()
                )
            }

            return chain.proceed(chain.request().newBuilder().build())
        }
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }
}