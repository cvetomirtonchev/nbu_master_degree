package tsvetomir.tonchev.findit.ui.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme {
                FindItNavHost(viewModel)
            }
        }
        handleObservers()
        viewModel.autoLogin()
    }

    private fun handleObservers() {
        lifecycleScope.launch {
            viewModel.loginButtonState.collectLatest {
                DashboardActivity.start(this@LoginActivity)
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