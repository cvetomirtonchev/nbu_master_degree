package tsvetomir.tonchev.findit.ui.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@ExperimentalUnitApi
@ExperimentalMaterial3Api
@Composable
fun DashboardHome(viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    NavigationPage(viewModel)
}

@ExperimentalUnitApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DashboardHomeContentPreview() {
    FindItTheme {
        DashboardHome()
    }
}