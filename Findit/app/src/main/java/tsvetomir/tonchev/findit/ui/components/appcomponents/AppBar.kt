package tsvetomir.tonchev.findit.ui.components.appcomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import tsvetomir.tonchev.findit.R

@Composable
fun AppBar(
    onNavigationItemClicked: () -> Unit
) {
    SmallTopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    },
        navigationIcon = {
            IconButton(onClick = onNavigationItemClicked) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle drawer")
            }
        })
}