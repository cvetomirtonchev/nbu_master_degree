package tsvetomir.tonchev.findit.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dagger.hilt.android.AndroidEntryPoint
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.components.ButtonType
import tsvetomir.tonchev.findit.ui.components.ButtonWithRoundCornerShape
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.SecondaryGreen


@ExperimentalComposeUiApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
@ExperimentalComposeUiApi
fun LoginScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val (images, title, usernamePassword, button) = createRefs()
        ImageList(
            Modifier
                .fillMaxWidth()
                .constrainAs(images) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp, top = 32.dp, end = 16.dp)
        )
        TitleAndDescription(
            Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(images.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp, top = 16.dp, end = 16.dp))
        UsernamePasswordRegister(
            Modifier
                .fillMaxWidth()
                .constrainAs(usernamePassword) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp, top = 16.dp, end = 16.dp))

        Buttons(
            Modifier
                .fillMaxWidth()
                .constrainAs(button) {
                    top.linkTo(usernamePassword.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    linkTo(usernamePassword.bottom, parent.bottom, bias = 1f, bottomMargin = 16.dp)
                    height = Dimension.preferredWrapContent
                }
                .padding(start = 16.dp, top = 16.dp, end = 16.dp))

    }
}

@Composable
fun ImageList(modifier: Modifier) {
    val list = listOf(
        painterResource(id = R.drawable.photo_food),
        painterResource(id = R.drawable.destination),
        painterResource(id = R.drawable.hotel)
    )
    LazyRow(modifier = modifier) {
        items(items = list, itemContent = { item ->
            Image(
                contentScale = ContentScale.Crop,
                painter = item,
                contentDescription = "Images",
                modifier = Modifier
                    // Set image size to 40 dp
                    .fillMaxSize()
                    .size(150.dp)
                    // Clip image to be shaped as a circle
                    .padding(end = 12.dp)
            )
        })
    }
}

@Composable
fun TitleAndDescription(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.welcome_title),
            color = SecondaryGreen,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "We are here to find what you are looking for now",
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun UsernamePasswordRegister(modifier: Modifier) {
    val username = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = modifier) {
        TextField(
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White
            ),
            label = { Text(text = "Username", style = MaterialTheme.typography.labelSmall) },
            value = username.value,
            onValueChange = {
                username.value = it
            },
            textStyle = MaterialTheme.typography.labelMedium,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() })
        )
        TextField(
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White
            ),
            label = { Text(text = "Password", style = MaterialTheme.typography.labelSmall) },
            value = password.value,
            onValueChange = {
                password.value = it
            },
            textStyle = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
        )
        ButtonWithRoundCornerShape(
            type = ButtonType.TEXT_BUTTON,
            title = "Register",
            onClick = {},
        )
    }
}

@Composable
fun Buttons(modifier: Modifier) {
    Column(modifier) {
        ButtonWithRoundCornerShape(
            title = "Login",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        ButtonWithRoundCornerShape(
            type = ButtonType.SECONDARY,
            title = "Skip Login",
            onClick = {},
            modifier = Modifier
                .padding(top = 16.dp)
                .height(42.dp)
        )
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FindItTheme {
        LoginScreen()
    }
}