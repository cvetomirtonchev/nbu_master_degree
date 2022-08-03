package tsvetomir.tonchev.findit.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.components.ButtonType
import tsvetomir.tonchev.findit.ui.components.ButtonWithRoundCornerShape
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.ui.login.LoginViewModel
import tsvetomir.tonchev.findit.ui.theme.ColorError
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.SecondaryGreen


@Composable
@ExperimentalComposeUiApi
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), navController: NavHostController) {
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
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            loginViewModel = loginViewModel,
            navController = navController)

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
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            loginViewModel = loginViewModel)
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
            text = stringResource(R.string.welcome_sub_title),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun UsernamePasswordRegister(
    modifier: Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        TextField(
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White
            ),
            label = {
                Text(
                    text = stringResource(R.string.username),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            value = loginViewModel.username.value.text,
            onValueChange = {
                loginViewModel.username.value = InputDataModel(text = it)
            },
            trailingIcon = {
                if (!loginViewModel.username.value.inputError.isNullOrEmpty())
                    Icon(Icons.Filled.Info, "error", tint = ColorError)
            },
            textStyle = MaterialTheme.typography.labelMedium,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() })
        )
        if (!loginViewModel.username.value.inputError.isNullOrEmpty()) {
            Text(
                text = loginViewModel.username.value.inputError ?: "",
                color = ColorError,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        TextField(
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            value = loginViewModel.password.value.text,
            onValueChange = {
                loginViewModel.password.value = InputDataModel(text = it)
            },
            textStyle = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                if (!loginViewModel.password.value.inputError.isNullOrEmpty())
                    Icon(Icons.Filled.Info, "error", tint = ColorError)
            },
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
        )
        if (!loginViewModel.password.value.inputError.isNullOrEmpty()) {
            Text(
                text = loginViewModel.password.value.inputError ?: "",
                color = ColorError,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        ButtonWithRoundCornerShape(
            type = ButtonType.TEXT_BUTTON,
            title = stringResource(R.string.register),
            onClick = {
                navController.navigate(Routes.Register.route)
            },
        )
    }
}

@Composable
fun Buttons(modifier: Modifier, loginViewModel: LoginViewModel = viewModel()) {
    Column(modifier) {
        ButtonWithRoundCornerShape(
            title = stringResource(R.string.login_btn),
            onClick = {
                loginViewModel.onLoginButtonClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        ButtonWithRoundCornerShape(
            type = ButtonType.SECONDARY,
            title = stringResource(R.string.skip_login_btn),
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
fun LoginScreenDefaultPreview() {
    FindItTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}