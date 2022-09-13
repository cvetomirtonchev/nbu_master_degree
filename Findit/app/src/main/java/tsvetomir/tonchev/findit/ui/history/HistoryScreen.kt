package tsvetomir.tonchev.findit.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tsvetomir.tonchev.findit.ui.dashboard.DashboardViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.utils.datastore.fromPlaceTypeToIconRes
import tsvetomir.tonchev.findit.utils.datastore.fromPlaceTypeToString

@ExperimentalMaterial3Api
@Composable
fun HistoryScreen(viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    viewModel.getSearchHistory()
    val historyModel: List<HistoryUiModel> = viewModel.searchHistoryMutableState.value
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(historyModel) { item ->
            HistoryElement(item)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun HistoryElement(historyList: HistoryUiModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(fromPlaceTypeToIconRes(historyList.placeType)),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = fromPlaceTypeToString(historyList.placeType).replace("_", " "),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "in ${historyList.city}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    FindItTheme {
        HistoryScreen()
    }
}
