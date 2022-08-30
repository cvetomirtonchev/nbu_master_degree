package tsvetomir.tonchev.findit.utils.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

class LocalDataStore @Inject constructor(private val dataStore: PreferenceDataStore) {
    suspend fun getSessionToken(): String =
        dataStore.getData(SESSION_TOKEN, "")

    suspend fun saveSessionToken(sessionToken: String): Unit =
        dataStore.saveData(sessionToken, SESSION_TOKEN)

    suspend fun removeSessionToken(): Unit =
        dataStore.removeData(SESSION_TOKEN)

    suspend fun isDisabilityEnabled(): Boolean =
        dataStore.getData(IS_DISABILITY_ENABLED, false)

    suspend fun setDisability(state: Boolean): Unit =
        dataStore.saveData(state, IS_DISABILITY_ENABLED)

    suspend fun clearAll(): Unit =
        dataStore.clearAll(emptyList())

    companion object {
        val SESSION_TOKEN = stringPreferencesKey("SESSION_TOKEN")
        val IS_DISABILITY_ENABLED = booleanPreferencesKey("IS_DISABILITY_ENABLED")
    }
}