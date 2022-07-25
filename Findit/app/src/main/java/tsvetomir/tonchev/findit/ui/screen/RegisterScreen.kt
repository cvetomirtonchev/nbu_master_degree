package tsvetomir.tonchev.findit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tsvetomir.tonchev.findit.ui.theme.FindItTheme

@Composable
fun RegisterScreen(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.primary)
    ) {

    }
}

@Composable
fun FirstNameField() {

}

@Composable
fun LastNameField() {

}

@Composable
fun EmailField() {

}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun RegisterScreenDefaultPreview() {
    FindItTheme {
        val navController = rememberNavController()
        RegisterScreen(navController = navController)
    }
}