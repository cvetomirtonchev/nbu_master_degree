package tsvetomir.tonchev.findit.ui.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.ui.base.BaseActivity
import tsvetomir.tonchev.findit.ui.dashboard.DashboardActivity
import tsvetomir.tonchev.findit.ui.screen.FindItNavHost
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.utils.shouldAskForLocationPermisions
import tsvetomir.tonchev.findit.utils.showLocationDialog


@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    override val viewModel: LoginViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            var isLocationEnabled = false
            it.forEach { permission ->
                if (permission.value) {
                    isLocationEnabled = true
                }
            }
            if (isLocationEnabled.not()) {
                showLocationDialog(this)
            }
        }

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLocationPermissionRequest()
        setContent {
            FindItTheme {
                FindItNavHost(viewModel)
            }
        }
        handleObservers()
        viewModel.autoLogin()
    }

    private fun startLocationPermissionRequest() {
        when {
            shouldAskForLocationPermisions(this) -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        }
    }

    @ExperimentalMaterial3Api
    @ExperimentalUnitApi
    private fun handleObservers() {
        lifecycleScope.launch {
            viewModel.loginButtonState.collectLatest {
                DashboardActivity.start(this@LoginActivity)
                finish()
            }
        }
    }

    companion object {
        fun start(activity: Activity) =
            activity.startActivity(Intent(activity, LoginActivity::class.java))
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FindItTheme {
        FindItNavHost()
    }
}