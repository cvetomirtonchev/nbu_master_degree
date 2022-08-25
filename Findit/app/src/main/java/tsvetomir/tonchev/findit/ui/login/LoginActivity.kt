package tsvetomir.tonchev.findit.ui.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.ui.base.BaseActivity
import tsvetomir.tonchev.findit.ui.dashboard.DashboardActivity
import tsvetomir.tonchev.findit.ui.screen.FindItNavHost
import tsvetomir.tonchev.findit.ui.theme.FindItTheme


@ExperimentalComposeUiApi
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    override val viewModel: LoginViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            it.forEach { permission ->
                if (permission.value) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // TODO Show screen to enable location
                    Toast.makeText(
                        this,
                        "Place enable location to be able to use the app",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLocationPermissionRequest()
        setContent {
            FindItTheme {
                FindItNavHost(viewModel)
            }
        }
        handleObservers()
//        viewModel.autoLogin()
    }

    private fun startLocationPermissionRequest() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
            else -> {
                // do nothing
            }
        }
    }

    private fun handleObservers() {
        lifecycleScope.launch {
            viewModel.loginButtonState.collectLatest {
                DashboardActivity.start(this@LoginActivity)
                finish()
            }
        }
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