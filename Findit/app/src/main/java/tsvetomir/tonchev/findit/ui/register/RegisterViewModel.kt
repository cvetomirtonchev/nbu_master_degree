package tsvetomir.tonchev.findit.ui.register

import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.data.network.model.request.SignupRequest
import tsvetomir.tonchev.findit.domain.repository.UserRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val provideDispatchersProvider: CoroutineDispatchersProvider,
    private val resources: Resources
) : BaseViewModel() {
    var firstName = mutableStateOf(InputDataModel())
    var lastName = mutableStateOf(InputDataModel())
    var email = mutableStateOf(InputDataModel())
    var username = mutableStateOf(InputDataModel())
    var dateOfBirth = mutableStateOf(InputDataModel())
    var password = mutableStateOf(InputDataModel())

    var isRegisterSuccessful = mutableStateOf(false)

    fun register() {
        showLoading()
        if (!isFieldsPopulated()) {
            return
        }

        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            val signupRequest = SignupRequest(
                firstName = firstName.value.text,
                lastName = lastName.value.text,
                email = email.value.text,
                username = username.value.text,
                dateOfBirth = dateOfBirth.value.text,
                password = password.value.text,
            )
            runCatching {
                userRepository.register(signupRequest)
            }.onSuccess {
                hideLoading()
                isRegisterSuccessful.value = true
            }.onFailure {
                hideLoading()
            }
        }
    }

    private fun isFieldsPopulated(): Boolean {
        when {
            firstName.value.text.isEmpty() -> {
                firstName.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
            lastName.value.text.isEmpty() -> {
                lastName.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
            email.value.text.isEmpty() -> {
                email.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
            username.value.text.isEmpty() -> {
                username.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
            dateOfBirth.value.text.isEmpty() -> {
                dateOfBirth.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
            password.value.text.isEmpty() -> {
                password.value =
                    InputDataModel(text = "", resources.getString(R.string.field_empty_error))
                return false
            }
        }
        return true
    }
}