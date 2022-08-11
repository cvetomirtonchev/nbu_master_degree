package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tsvetomir.tonchev.findit.ui.screen.NavigationPage
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@ExperimentalMaterial3Api
@Composable
fun DashboardHome() {
    NavigationPage()
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DashboardHomeContentPreview() {
    FindItTheme {
        DashboardHome()
    }
}