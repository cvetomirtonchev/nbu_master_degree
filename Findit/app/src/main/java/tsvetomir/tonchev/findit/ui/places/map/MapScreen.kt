package tsvetomir.tonchev.findit.ui.places.map

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.places.PlacesViewModel

@ExperimentalMaterial3Api
@Composable
fun MapScreen(location: Location, placesViewModel: PlacesViewModel = viewModel()) {
    val context = LocalContext.current
    Column {
        val currentLocation = LatLng(location.latitude, location.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentLocation, 13f)
        }
        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ContextCompat.getDrawable(context, R.drawable.ic_my_location)?.toBitmap()?.let {
                Marker(
                    state = MarkerState(position = currentLocation),
                    icon = BitmapDescriptorFactory.fromBitmap(it)
                )
            }

            placesViewModel.placesUiModelList.value.forEach { placeUiModel ->
                Marker(
                    state = MarkerState(position = LatLng(placeUiModel.lat, placeUiModel.lng)),
                    title = placeUiModel.name,
                    snippet = "Click to see the details",
                    onInfoWindowLongClick = {
//                        viewModel.onEvent(
//                            MapEvent.OnInfoWindowLongClick(spot)
//                        )
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED
                    )
                )
            }
        }
    }
}