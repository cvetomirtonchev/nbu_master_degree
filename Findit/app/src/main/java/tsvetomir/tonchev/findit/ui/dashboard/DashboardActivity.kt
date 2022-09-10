package tsvetomir.tonchev.findit.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import dagger.hilt.android.AndroidEntryPoint
import tsvetomir.tonchev.findit.ui.base.BaseActivity
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@ExperimentalComposeUiApi
@ExperimentalUnitApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    override val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isDisabilityEnabled()
        setContent {
            FindItTheme {
                DashboardHome(viewModel)
            }
        }
    }

    companion object {
        fun start(activity: Activity) =
            activity.startActivity(Intent(activity, DashboardActivity::class.java))
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FindItTheme {
    }
}