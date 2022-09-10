package tsvetomir.tonchev.findit.ui.places

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import tsvetomir.tonchev.findit.ui.places.map.MapScreen

@ExperimentalMaterial3Api
@Composable
fun PlacesScreen(location: Location, placesViewModel: PlacesViewModel = viewModel()) {
    Column {
        MapScreen(location, placesViewModel)
    }
}