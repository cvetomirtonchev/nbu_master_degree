package tsvetomir.tonchev.findit.ui.places

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import tsvetomir.tonchev.findit.ui.base.BaseActivity
import tsvetomir.tonchev.findit.ui.components.CircularIndeterminateProgressBar
import tsvetomir.tonchev.findit.ui.explore.PlaceModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@ExperimentalMaterial3Api
@AndroidEntryPoint
class PlacesActivity : BaseActivity() {

    override val viewModel: PlacesViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val placeModel = intent.getParcelableExtra<PlaceModel>(PLACE_MODEL_EXTRA)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    viewModel.loadPlacesNearYou(placeModel, location)
                    setContent {
                        FindItTheme {
                            PlacesScreen(location, viewModel)
                            CircularIndeterminateProgressBar(viewModel)
                        }
                    }
                }
            }
    }

    companion object {
        private const val PLACE_MODEL_EXTRA = "PLACE_MODEL_EXTRA"
        fun start(context: Context, placeModel: PlaceModel) =
            context.startActivity(Intent(context, PlacesActivity::class.java).apply {
                putExtra(PLACE_MODEL_EXTRA, placeModel)
            })
    }
}