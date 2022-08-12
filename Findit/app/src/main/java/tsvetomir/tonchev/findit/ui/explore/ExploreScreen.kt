package tsvetomir.tonchev.findit.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tsvetomir.tonchev.findit.domain.repository.PlacesRepository
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

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
        Image(
            painter = painterResource(id = exploreModel.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = exploreModel.title)

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