package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import tsvetomir.tonchev.findit.utils.datastore.fromPlaceTypeToString
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val provideDispatchersProvider: CoroutineDispatchersProvider,
) :
    BaseViewModel() {

    fun loadPlacesNearYou(placeModel: PlaceModel?, location: Location) {
        if (placeModel == null) {
            return
        }
        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching {
                placesRepository.findAllPlaces(
                    fromPlaceTypeToString(placeModel.placeType),
                    location
                )
            }
        }
    }
}