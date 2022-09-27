package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import tsvetomir.tonchev.findit.utils.fromPlaceTypeToString
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val provideDispatchersProvider: CoroutineDispatchersProvider,
) : BaseViewModel() {

    val placesUiModelList = mutableStateOf<List<PlaceUiModel>>(emptyList())

    fun loadPlacesNearYou(placeModel: PlaceModel?, location: Location, cityName: String) {
        if (placeModel == null) {
            return
        }
        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching {
                placesRepository.findAllPlaces(
                    fromPlaceTypeToString(placeModel.placeType),
                    location,
                    cityName
                )
            }.onSuccess {
                hideLoading()
                placesUiModelList.value = it
            }.onFailure { hideLoading() }
        }
    }

    fun markAsAccessible(placeUiModel: PlaceUiModel) {
        placeUiModel.forDisability = true
        placesUiModelList.value = placesUiModelList.value.map {
            if (placeUiModel.id == it.id) {
                placeUiModel
            } else it
        }
        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching { placesRepository.addAccessiblePlace(placeUiModel) }
                .onSuccess { hideLoading() }
                .onFailure { hideLoading() }
        }
    }
}