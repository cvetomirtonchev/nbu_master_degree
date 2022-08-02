package tsvetomir.tonchev.findit.utils.datastore

import androidx.annotation.VisibleForTesting
import androidx.datastore.preferences.core.stringPreferencesKey
import tsvetomir.tonchev.findit.utils.PreferenceDataStore
import javax.inject.Inject

class LocalDataStore @Inject constructor(private val dataStore: PreferenceDataStore) {
    suspend fun getSessionToken(): String =
        dataStore.getData(SESSION_TOKEN, "")

    suspend fun saveSessionToken(sessionToken: String): Unit =
        dataStore.saveData(sessionToken, SESSION_TOKEN)

    suspend fun removeSessionToken(): Unit =
        dataStore.removeData(SESSION_TOKEN)

    suspend fun clearAll(): Unit =
        dataStore.clearAll(emptyList())

    companion object {
        @VisibleForTesting
        val SESSION_TOKEN = stringPreferencesKey("SESSION_TOKEN")
    }
}