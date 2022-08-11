package tsvetomir.tonchev.findit.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.ui.theme.ColorError

@ExperimentalComposeUiApi
@Composable
fun InputValueField(
    label: String,
    inputDataModel: MutableState<InputDataModel>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.White
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        value = inputDataModel.value.text,
        onValueChange = {
            inputDataModel.value = InputDataModel(text = it)
        },
        trailingIcon = {
            if (!inputDataModel.value.inputError.isNullOrEmpty())
                Icon(Icons.Filled.Info, "error", tint = ColorError)
        },
        textStyle = MaterialTheme.typography.labelMedium,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() })
    )
    if (!inputDataModel.value.inputError.isNullOrEmpty()) {
        Text(
            text = inputDataModel.value.inputError ?: "",
            color = ColorError,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}


@ExperimentalComposeUiApi
@Composable
fun InputPasswordValueField(
    modifier: Modifier,
    label: String,
    inputDataModel: MutableState<InputDataModel>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.White
        ),
        visualTransformation = PasswordVisualTransformation(),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        value = inputDataModel.value.text,
        onValueChange = {
            inputDataModel.value = InputDataModel(text = it)
        },
        textStyle = MaterialTheme.typography.labelMedium,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
            if (!inputDataModel.value.inputError.isNullOrEmpty())
                Icon(Icons.Filled.Info, "error", tint = ColorError)
        },
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
    )
    if (!inputDataModel.value.inputError.isNullOrEmpty()) {
        Text(
            text = inputDataModel.value.inputError ?: "",
            color = ColorError,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}