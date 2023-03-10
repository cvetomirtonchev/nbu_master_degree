package tsvetomir.tonchev.findit.ui.places

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Build
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
import java.util.*


@ExperimentalMaterial3Api
@AndroidEntryPoint
class PlacesActivity : BaseActivity() {

    override val viewModel: PlacesViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val placeModel = intent.getParcelableExtra<PlaceModel>(PLACE_MODEL_EXTRA)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    getCityName(location) { city ->
                        viewModel.loadPlacesNearYou(placeModel, location, city)
                    }
                    setContent {
                        FindItTheme {
                            PlacesNavigation(viewModel, location)
                            CircularIndeterminateProgressBar(viewModel)
                        }
                    }
                }
            }
    }

    private fun getCityName(location: Location, onCityProvided: (String) -> Unit) {
        val geocoder = Geocoder(this, Locale.US)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(location.latitude, location.longitude, 1) {
                onCityProvided(it.first().locality)
            }
        } else {
            val city = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                ?.first()?.locality
            onCityProvided(city ?: throw IllegalArgumentException("Missing city"))
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