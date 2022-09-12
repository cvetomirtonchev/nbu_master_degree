package tsvetomir.tonchev.findit.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.data.network.model.response.User
import tsvetomir.tonchev.findit.ui.components.InputValueField
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.ui.dashboard.DashboardViewModel
import tsvetomir.tonchev.findit.ui.theme.FindItTheme
import tsvetomir.tonchev.findit.ui.theme.WhiteColorTransparent

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val user: User? = viewModel.userMutableState.value
    user?.let {
        ProfileCard(it, viewModel)
    } ?: run {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.secondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.login_msg),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.width(12.dp))
            AccessibilityField(viewModel)
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun ProfileCard(
    user: User,
    viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        HeaderProfile(user)
        Card(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            ProfileSettings(user, viewModel)
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun ProfileSettings(user: User, viewModel: DashboardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_settings),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        val username = remember {
            mutableStateOf(InputDataModel(user.username))
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        InputValueField(stringResource(id = R.string.username), username)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        AccessibilityField(viewModel)
    }
}

@Composable
fun AccessibilityField(viewModel: DashboardViewModel) {
    val isDisabilityEnabled = viewModel.isDisabilityEnabledState
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_accessible),
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(R.string.accessability) + ":",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(2.dp))
        Switch(checked = isDisabilityEnabled.value, onCheckedChange = {
            viewModel.setDisabilityState(it)

        }, colors = SwitchDefaults.colors(checkedThumbColor = Color.White))
    }
}

@Composable
fun HeaderProfile(user: User) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .clip(CircleShape)
                .background(WhiteColorTransparent)
                .width(72.dp)
                .height(72.dp)
        ) {
            Icon(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                imageVector = Icons.Default.Person, contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}


@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    FindItTheme {
        val user = User("tsvetomir1", "johndoe@mail.com", "John", "Doe", "19.02.1996")
        ProfileCard(user)
    }
}