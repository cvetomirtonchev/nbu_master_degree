package tsvetomir.tonchev.findit.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tsvetomir.tonchev.findit.ui.dashboard.DashboardViewModel

@Composable
fun ProfileScreen(viewModel: DashboardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "Profile")
    }
}