package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    sessionStorage: SessionStorage,
    private val localDataStore: LocalDataStore,
    private val placesRepository: PlacesRepository,
    private val dispatcher: CoroutineDispatchersProvider
) :
    BaseViewModel() {
    val userMutableState = mutableStateOf(sessionStorage.user)
    val isDisabilityEnabledState = mutableStateOf(false)
    val searchHistoryMutableState = mutableStateOf<List<HistoryUiModel>>(emptyList())

    fun isDisabilityEnabled() {
        viewModelScope.launch {
            isDisabilityEnabledState.value = localDataStore.isDisabilityEnabled()
        }
    }

    fun setDisabilityState(isEnabled: Boolean) {
        showLoading()
        viewModelScope.launch {
            localDataStore.setDisability(isEnabled)
        }
        isDisabilityEnabledState.value = isEnabled
        hideLoading()
    }

    fun getSearchHistory() {
        viewModelScope.launch(dispatcher.io) {
            searchHistoryMutableState.value = placesRepository.getSearchHistory().map {
                HistoryUiModel(fromStringToPlaceType(it.searchType), it.city)
            }
        }
    }
}