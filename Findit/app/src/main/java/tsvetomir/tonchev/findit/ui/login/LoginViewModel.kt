package tsvetomir.tonchev.findit.ui.login

import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import tsvetomir.tonchev.findit.R
import tsvetomir.tonchev.findit.domain.repository.UserRepository
import tsvetomir.tonchev.findit.ui.base.BaseViewModel
import tsvetomir.tonchev.findit.ui.components.model.InputDataModel
import tsvetomir.tonchev.findit.utils.CoroutineDispatchersProvider
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val provideDispatchersProvider: CoroutineDispatchersProvider,
    private val resources: Resources
) : BaseViewModel() {

    private val _loginButtonState = MutableSharedFlow<Unit>()
    val loginButtonState: SharedFlow<Unit> = _loginButtonState

    //TODO remove hardcoded values
    var username = mutableStateOf(InputDataModel(text = "cvetomir"))
    var password = mutableStateOf(InputDataModel(text = "123456"))

    fun onLoginButtonClicked() {
        when {
            username.value.text.isEmpty() -> {
                username.value =
                    InputDataModel(text = "", resources.getString(R.string.empty_username_error))
                return
            }
            password.value.text.isEmpty() -> {
                password.value =
                    InputDataModel(text = "", resources.getString(R.string.empty_password_error))
                return
            }
        }

        showLoading()
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching {
                userRepository.login(username.value.text, password.value.text)
            }.onSuccess {
                hideLoading()
                _loginButtonState.emit(Unit)
            }.onFailure {
                hideLoading()
            }
        }
    }

    fun onSkipLoginClicked() {
        viewModelScope.launch {
            _loginButtonState.emit(Unit)
        }
    }

    fun autoLogin() {
        viewModelScope.launch(provideDispatchersProvider.io) {
            runCatching {
                userRepository.loadUserProfile()
            }.onSuccess {
                hideLoading()
                _loginButtonState.emit(Unit)
            }.onFailure {
                hideLoading()
            }
        }
    }
}