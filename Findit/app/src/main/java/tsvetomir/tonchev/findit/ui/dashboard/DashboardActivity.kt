package tsvetomir.tonchev.findit.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@OptIn(ExperimentalMaterial3Api::class)
class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardHome()
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