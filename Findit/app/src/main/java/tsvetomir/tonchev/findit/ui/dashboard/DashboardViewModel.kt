package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.utils.datastore.LocalDataStore
import tsvetomir.tonchev.findit.utils.datastore.SessionStorage
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    sessionStorage: SessionStorage,
    private val localDataStore: LocalDataStore
) :
    BaseViewModel() {
    val userMutableState = mutableStateOf(sessionStorage.user)
    val isDisabilityEnabledState = mutableStateOf(false)

    fun isDisabilityEnabled() {
        viewModelScope.launch {
            isDisabilityEnabledState.value = localDataStore.isDisabilityEnabled()
        }
    }
}