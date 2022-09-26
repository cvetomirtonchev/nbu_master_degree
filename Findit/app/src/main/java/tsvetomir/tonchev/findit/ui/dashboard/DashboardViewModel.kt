package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.history.HistoryUiModel
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import tsvetomir.tonchev.findit.utils.datastore.LocalDataStore
import tsvetomir.tonchev.findit.utils.datastore.SessionStorage
import tsvetomir.tonchev.findit.utils.datastore.fromStringToPlaceType
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val sessionStorage: SessionStorage,
    private val localDataStore: LocalDataStore,
    private val placesRepository: PlacesRepository,
    private val dispatcher: CoroutineDispatchersProvider
) : BaseViewModel() {
    private val _logoutButtonState = MutableSharedFlow<Unit>()
    val logoutButtonState: SharedFlow<Unit> = _logoutButtonState

    val userMutableState = mutableStateOf(sessionStorage.user)
    val searchHistoryMutableState = mutableStateOf<List<HistoryUiModel>>(emptyList())

    fun getSearchHistory() {
        viewModelScope.launch(dispatcher.io) {
            searchHistoryMutableState.value = placesRepository.getSearchHistory().map {
                HistoryUiModel(fromStringToPlaceType(it.searchType), it.city)
            }
        }
    }

    fun logout() {
        viewModelScope.launch(dispatcher.io) {
            placesRepository.clearSearchHistory()
            sessionStorage.user = null
            localDataStore.removeSessionToken()
            _logoutButtonState.emit(Unit)
        }
    }
}