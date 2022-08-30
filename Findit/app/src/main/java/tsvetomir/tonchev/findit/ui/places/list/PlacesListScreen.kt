package tsvetomir.tonchev.findit.ui.places.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.ui.places.PlacesViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.YellowColor
import tsvetomir.tonchev.findit.utils.datastore.openDirectionsInMaps

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
            PlaceItemView(item, placesViewModel)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PlaceItemView(
    placeUiModel: PlaceUiModel,
    viewModel: PlacesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val isAccessible = remember {
        mutableStateOf(placeUiModel.forDisability)
    }
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = placeUiModel.name,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
            RatingBarView(placeUiModel.rating)

            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = placeUiModel.address,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Accessibility:",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
                Icon(
                    painter = if (isAccessible.value) painterResource(id = R.drawable.ic_accessible) else painterResource(
                        id = R.drawable.ic_not_accessible
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Spacer(modifier = Modifier.size(6.dp))
            PlaceButtons(placeUiModel, viewModel) {
                isAccessible.value = true
            }
        }
    }
}

@Composable
fun PlaceButtons(
    placeUiModel: PlaceUiModel,
    viewModel: PlacesViewModel,
    onAccessibleClicked: () -> Unit
) {
    val isAccessible = remember {
        mutableStateOf(placeUiModel.forDisability)
    }
    val context = LocalContext.current
    Row {
        Button(onClick = {
            openDirectionsInMaps(context, placeUiModel.lat, placeUiModel.lng)
        }, contentPadding = ButtonDefaults.TextButtonContentPadding) {
            Icon(
                Icons.Filled.Place,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = "Get directions",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (isAccessible.value.not()) {
            Spacer(modifier = Modifier.size(6.dp))
            Button(onClick = {
                onAccessibleClicked()
                isAccessible.value = true
                viewModel.markAsAccessible(placeUiModel)
            }, contentPadding = ButtonDefaults.TextButtonContentPadding) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = "Mark as accessible",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun RatingBarView(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            var painter = painterResource(R.drawable.ic_star)
            var color = Color.Gray
            val halfDigit: Double = i - rating
            if (halfDigit > 0 && halfDigit < 1) {
                painter = painterResource(id = R.drawable.ic_half_star)
                color = YellowColor
            } else if (i <= rating) {
                color = YellowColor
            }
            Icon(
                painter = painter,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
        }
        Text(
            text = "$rating",
            style = MaterialTheme.typography.labelMedium,
            color = YellowColor,
            modifier = Modifier.padding(start = 2.dp)
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
                2.9,
                "Mladost 1, Sofia",
                false,
                "Sofia"
            )
        )
    }
}