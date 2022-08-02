package tsvetomir.tonchev.findit.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

interface PreferenceDataStore {

    suspend fun <T> getData(key: Preferences.Key<T>, replacementValue: T): T

    fun <T> getDataFlow(key: Preferences.Key<T>, replacementValue: T): Flow<T>

    suspend fun <T> saveData(data: T, key: Preferences.Key<T>)

    suspend fun <T> removeData(key: Preferences.Key<T>)

    suspend fun clearAll(keysToSave: List<Preferences.Key<*>>)
}

class PreferenceDataStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PreferenceDataStore {

    override suspend fun <T> getData(key: Preferences.Key<T>, replacementValue: T): T =
        getDataFlow(key, replacementValue).first()

    override fun <T> getDataFlow(key: Preferences.Key<T>, replacementValue: T): Flow<T> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: replacementValue
            }

    override suspend fun <T> saveData(data: T, key: Preferences.Key<T>) {
        dataStore.edit { settings ->
            settings[key] = data
        }
    }

    override suspend fun <T> removeData(key: Preferences.Key<T>) {
        dataStore.edit { settings ->
            settings.remove(key)
        }
    }

    override suspend fun clearAll(keysToSave: List<Preferences.Key<*>>) {
        dataStore.edit { settings ->
            settings.asMap().forEach {
                if (!keysToSave.contains(it.key)) {
                    settings.remove(it.key)
                }
            }
        }
    }
}