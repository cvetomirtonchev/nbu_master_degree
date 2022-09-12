package tsvetomir.tonchev.findit.ui.register

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.preferredWrapContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.components.ButtonType
import tsvetomir.tonchev.findit.ui.components.ButtonWithRoundCornerShape
import tsvetomir.tonchev.findit.ui.components.InputPasswordValueField
import tsvetomir.tonchev.findit.ui.components.InputValueField
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.ui.theme.ColorError
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import java.util.*


@ExperimentalComposeUiApi
@Composable
fun RegisterScreen(
    navController: NavHostController
) {
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    if (registerViewModel.isRegisterSuccessful.value) {
        navController.navigateUp()
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val (column, button) = createRefs()
        Column(
            Modifier
                .fillMaxWidth()
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp, top = 32.dp, end = 16.dp)
        ) {
            InputValueField("First Name", registerViewModel.firstName)
            Spacer(modifier = Modifier.padding(top = 12.dp))

            InputValueField("Last Name", registerViewModel.lastName)
            Spacer(modifier = Modifier.padding(top = 12.dp))

            InputValueField("Email", registerViewModel.email)
            Spacer(modifier = Modifier.padding(top = 12.dp))

            InputValueField("Username", registerViewModel.username)
            Spacer(modifier = Modifier.padding(top = 12.dp))

            DateOfBirthFiled("Date of birth", registerViewModel.dateOfBirth)
            Spacer(modifier = Modifier.padding(top = 12.dp))

            InputPasswordValueField(
                Modifier
                    .fillMaxWidth(),
                stringResource(id = R.string.password),
                registerViewModel.password
            )
        }
        RegisterButtons(
            Modifier
                .fillMaxWidth()
                .constrainAs(button) {
                    top.linkTo(column.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    linkTo(column.bottom, parent.bottom, bias = 1f, bottomMargin = 16.dp)
                    height = preferredWrapContent
                }
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            registerViewModel = registerViewModel, navController)
    }
}


@Composable
fun DateOfBirthFiled(
    label: String,
    inputDataModel: MutableState<InputDataModel>
) {
    val context = LocalContext.current
    TextField(
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.White,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Gray
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        value = inputDataModel.value.text,
        onValueChange = {},
        trailingIcon = {
            if (!inputDataModel.value.inputError.isNullOrEmpty())
                Icon(Icons.Filled.Info, "error", tint = ColorError)
        },
        textStyle = MaterialTheme.typography.labelMedium,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                DatePickerDialog(
                    context, { _: DatePicker, _day: Int, _month: Int, _year: Int ->
                        inputDataModel.value = InputDataModel(text = "$_day/$_month/$_year")
                    }, year, month, day
                ).apply {
                    datePicker.maxDate = Date().time
                    show()
                }
            },
        enabled = false,
    )
    if (!inputDataModel.value.inputError.isNullOrEmpty()) {
        Text(
            text = inputDataModel.value.inputError ?: "",
            color = ColorError,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun RegisterButtons(
    modifier: Modifier,
    registerViewModel: RegisterViewModel = viewModel(),
    navController: NavHostController
) {
    Column(modifier) {
        ButtonWithRoundCornerShape(
            title = stringResource(R.string.register),
            onClick = {
                registerViewModel.register()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        ButtonWithRoundCornerShape(
            type = ButtonType.SECONDARY,
            title = stringResource(R.string.cancel),
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(42.dp)
        )
    }
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
