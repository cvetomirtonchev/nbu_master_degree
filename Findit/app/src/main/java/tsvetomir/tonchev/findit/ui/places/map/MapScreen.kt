package tsvetomir.tonchev.findit.ui.places.map

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import tsvetomir.tonchev.findit.ui.places.PlacesViewModel

@ExperimentalMaterial3Api
@Composable
fun MapScreen(location: Location, placesViewModel: PlacesViewModel) {
    Column {
        val currentLocation = LatLng(location.latitude, location.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentLocation, 13f)
        }
        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize(),
//            properties = viewModel.state.properties,
            onMapLongClick = {
//                viewModel.onEvent(MapEvent.OnMapLongClick(it))
            }
        ) {
//            viewModel.state.parkingSpots.forEach { spot ->
//                Marker(
//                    position = LatLng(spot.lat, spot.lng),
//                    title = "Parking spot (${spot.lat}, ${spot.lng})",
//                    snippet = "Long click to delete",
//                    onInfoWindowLongClick = {
//                        viewModel.onEvent(
//                            MapEvent.OnInfoWindowLongClick(spot)
//                        )
//                    },
//                    onClick = {
//                        it.showInfoWindow()
//                        true
//                    },
//                    icon = BitmapDescriptorFactory.defaultMarker(
//                        BitmapDescriptorFactory.HUE_GREEN
//                    )
//                )
//            }
        }
    }
}