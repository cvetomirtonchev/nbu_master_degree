package tsvetomir.tonchev.findit.ui.components.appcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onNavigationItemClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = onNavigationItemClicked) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer",
                    tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}