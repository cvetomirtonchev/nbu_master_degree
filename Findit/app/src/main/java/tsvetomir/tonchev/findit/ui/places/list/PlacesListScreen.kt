package tsvetomir.tonchev.findit.ui.places.list

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.ui.places.PlacesViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@ExperimentalMaterial3Api
@Composable
fun PlacesList(placesViewModel: PlacesViewModel) {
    val places = placesViewModel.placesUiModelList.value
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(places) { item ->
            PlaceItemView(item)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PlaceItemView(placeUiModel: PlaceUiModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Text(
            text = placeUiModel.name,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PlaceItemPreview() {
    FindItTheme {
        PlaceItemView(
            PlaceUiModel(
                "",
                1.0,
                1.0,
                "Happy Bar&Grill",
                emptyList(),
                5.0,
                "Address",
                true
            )
        )
    }
}