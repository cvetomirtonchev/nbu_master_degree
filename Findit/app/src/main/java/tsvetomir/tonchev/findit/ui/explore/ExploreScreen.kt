package tsvetomir.tonchev.findit.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.places.PlacesActivity
import tsvetomir.tonchev.findit.ui.theme.ColorBlackOverlay
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.WhiteColorTransparent

@ExperimentalMaterial3Api
@Composable
fun ExploreScreen() {
    val places = PlacesRepository.generatePlaces()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(places) { exploreModel ->
            ExploreCard(exploreModel)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ExploreCard(exploreModel: ExploreModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(160.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = exploreModel.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Surface(
                color = ColorBlackOverlay,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ExploreCardContent(exploreModel)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ExploreCardContent(exploreModel: ExploreModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val (text, row) = createRefs()
        Text(
            text = exploreModel.title,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(row) {
                top.linkTo(text.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                linkTo(text.bottom, parent.bottom, bias = 1f, bottomMargin = 8.dp)
                height = Dimension.preferredWrapContent
            }) {
            items(exploreModel.placeModels) { placeModel ->
                PlaceModel(placeModel)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PlaceModel(placeModel: PlaceModel) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(6.dp, 0.dp)
            .clickable {
                PlacesActivity.start(context, placeModel)
            }
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(WhiteColorTransparent)
        ) {
            Image(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(id = placeModel.icon), contentDescription = null
            )
        }
        Text(
            text = placeModel.title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewExplore() {
    FindItTheme {
        ExploreScreen()
    }
}