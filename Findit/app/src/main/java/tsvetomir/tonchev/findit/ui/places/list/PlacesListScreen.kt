@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures
import tsvetomir.tonchev.findit.domain.model.AccessibleFeatures.*
import tsvetomir.tonchev.findit.domain.model.AccessibleFeaturesUiModel
import tsvetomir.tonchev.findit.domain.model.PlaceUiModel
import tsvetomir.tonchev.findit.ui.components.ButtonType
import tsvetomir.tonchev.findit.ui.components.ButtonWithRoundCornerShape
import tsvetomir.tonchev.findit.ui.places.PlacesViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.YellowColor
import tsvetomir.tonchev.findit.utils.accessibleFeatureToRes
import tsvetomir.tonchev.findit.utils.openDirectionsInMaps

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
        mutableStateOf(placeUiModel.isAccessible)
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = stringResource(R.string.accessibility),
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
            if (isAccessible.value) {
                Spacer(modifier = Modifier.size(6.dp))
                AccessibleFeatures(placeUiModel.accessibleFeaturesUiModelList)
            }
            Spacer(modifier = Modifier.size(6.dp))
            PlaceButtons(placeUiModel, viewModel) {
                isAccessible.value = true
            }
        }
    }
}

@Composable
fun AccessibleFeatures(accessibleFeatures: List<AccessibleFeaturesUiModel>) {
    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 3)
    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
    ) {
        repeat(accessibleFeatures.size) { index ->
            val accessibleFeature = accessibleFeatures[index]
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.width(itemSize)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = accessibleFeature.accessibleFeatureImageVector,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = stringResource(id = accessibleFeature.accessibleFeaturesResIds),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
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
        mutableStateOf(placeUiModel.isAccessible)
    }

    val user = viewModel.userMutableState
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
                text = stringResource(R.string.get_directions),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (isAccessible.value.not() && user.value != null) {
            val openDialog = remember { mutableStateOf(false) }
            AccessibleDialog(openDialog.value) { isAdded, acceptedFeatures ->
                openDialog.value = false
                if (isAdded) {
                    onAccessibleClicked()
                    isAccessible.value = isAdded
                    viewModel.markAsAccessible(placeUiModel, acceptedFeatures)
                }
            }
            Spacer(modifier = Modifier.size(6.dp))
            Button(onClick = {
                openDialog.value = true
            }, contentPadding = ButtonDefaults.TextButtonContentPadding) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(R.string.mark_as_accessible),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AccessibleDialog(
    shouldOpenDialog: Boolean,
    onDismiss: (isAdded: Boolean, features: List<AccessibleFeatures>) -> Unit
) {
    val listOfFeatures = listOf(
        ENTRANCE,
        RESTROOM,
        PARKING,
        SEATING
    )
    val acceptedFeatures = mutableListOf<AccessibleFeatures>()
    acceptedFeatures.add(ENTRANCE)

    if (shouldOpenDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss(false, emptyList())
            },
            title = {
                Text(
                    text = stringResource(R.string.chose_accesible_features),
                    style = MaterialTheme.typography.labelMedium
                )
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(listOfFeatures) { feature ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val checkedState = remember {
                                if (feature == ENTRANCE) {
                                    mutableStateOf(true)
                                } else {
                                    mutableStateOf(false)
                                }
                            }
                            Checkbox(
                                colors = CheckboxDefaults.colors(checkmarkColor = Color.White),
                                checked = checkedState.value,
                                onCheckedChange = { isChecked ->
                                    checkedState.value = isChecked
                                    if (isChecked) {
                                        acceptedFeatures.add(feature)
                                    } else {
                                        acceptedFeatures.remove(feature)
                                    }
                                },
                                enabled = feature != ENTRANCE
                            )
                            Text(text = stringResource(id = accessibleFeatureToRes(feature)))
                        }
                    }
                }
            },
            dismissButton = {
                Column {
                    ButtonWithRoundCornerShape(
                        title = stringResource(R.string.dismiss),
                        type = ButtonType.PRIMARY
                    ) {
                        onDismiss(false, emptyList())
                    }
                }
            },
            confirmButton = {
                Column {
                    ButtonWithRoundCornerShape(
                        title = stringResource(R.string.add),
                        type = ButtonType.SECONDARY
                    ) {
                        onDismiss(true, acceptedFeatures)
                    }
                }
            }
        )
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
@Preview(showBackground = true, backgroundColor = 12)
@Composable
fun PlaceItemPreview() {
    FindItTheme {
//        PlaceItemView(
//            PlaceUiModel(
//                "",
//                1.0,
//                1.0,
//                "Happy Bar&Grill",
//                emptyList(),
//                2.9,
//                "Mladost 1, Sofia",
//                false,
//                "Sofia",
//                "type"
//            )
//        )
    }
}