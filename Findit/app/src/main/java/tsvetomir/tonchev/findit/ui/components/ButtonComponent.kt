package tsvetomir.tonchev.findit.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.WhiteColor

@Composable
fun ButtonWithRoundCornerShape(
    modifier: Modifier = Modifier,
    title: String,
    type: ButtonType = ButtonType.PRIMARY,
    icon: Int = 0,
    onClick: () -> Unit
) {
    val buttonColor = when (type) {
        ButtonType.PRIMARY -> WhiteColor
        else ->
            MaterialTheme.colorScheme.primary
    }

    val textColor = when (type) {
        ButtonType.PRIMARY -> MaterialTheme.colorScheme.primary
        else ->
            WhiteColor
    }

    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (text, image) = createRefs()
            Text(
                text = title,
                color = textColor,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
            if (icon != 0) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Image start",
                    modifier = Modifier
                        .size(36.dp)
                        .constrainAs(image) {
                            top.linkTo(text.top)
                            end.linkTo(text.start, margin = 8.dp)
                            bottom.linkTo(text.bottom)
                        }
                )
            }
        }
    }

}

class Render() {
    @Preview(showBackground = true, backgroundColor = 0x989a82)
    @Composable
    fun PreviewButton() {
        FindItTheme {
            Column {
                ButtonWithRoundCornerShape(
                    title = "Button",
                    onClick = {},
                    modifier = Modifier.padding(16.dp)
                )
                ButtonWithRoundCornerShape(
                    title = "Button",
                    onClick = {},
                    icon = R.drawable.ic_launcher_foreground,
                    modifier = Modifier.padding(16.dp),
                    type = ButtonType.SECONDARY
                )
            }
        }
    }
}

enum class ButtonType {
    PRIMARY,
    SECONDARY
}