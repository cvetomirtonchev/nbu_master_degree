package tsvetomir.tonchev.findit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.theme.ColorProgressBackground


@Composable
fun CircularIndeterminateProgressBar(baseViewModel: BaseViewModel = viewModel()) {
    val isDisplayed = baseViewModel.loading.value
    if (isDisplayed) {
        Surface(
            color = ColorProgressBackground, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x989a82)
@Composable
fun PreviewProgressBar() {
    CircularIndeterminateProgressBar()
}