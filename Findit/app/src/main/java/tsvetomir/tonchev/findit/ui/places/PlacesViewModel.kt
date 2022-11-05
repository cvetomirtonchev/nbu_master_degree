package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
import tsvetomir.tonchev.findit.domain.model.AccessibleFeaturesUiModel
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.utils.*
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val provideDispatchersProvider: CoroutineDispatchersProvider,
    sessionStorage: SessionStorage
) : BaseViewModel() {

    val placesUiModelList = mutableStateOf<List<PlaceUiModel>>(emptyList())
    val userMutableState = mutableStateOf(sessionStorage.user)

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

    fun markAsAccessible(placeUiModel: PlaceUiModel, acceptedFeatures: List<AccessibleFeatures>) {
        placeUiModel.isAccessible = true
        placeUiModel.accessibleFeaturesUiModelList = acceptedFeatures.map {
            AccessibleFeaturesUiModel(
                accessibleFeaturesResIds = accessibleFeatureToRes(it),
                accessibleFeatureImageVector = accessibleFeatureToImageVector(it)
            )
        }
        placesUiModelList.value = placesUiModelList.value.map {
            if (placeUiModel.id == it.id) {
                placeUiModel
            } else it
        }
        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching { placesRepository.addAccessiblePlace(placeUiModel, acceptedFeatures) }
                .onSuccess { hideLoading() }
                .onFailure { hideLoading() }
        }
    }
}