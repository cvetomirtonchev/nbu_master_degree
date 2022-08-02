package tsvetomir.tonchev.findit.domain.repository

import tsvetomir.tonchev.findit.data.network.model.request.LoginRequest
import tsvetomir.tonchev.findit.data.network.model.request.SignupRequest
import tsvetomir.tonchev.findit.data.network.model.response.User
import tsvetomir.tonchev.findit.data.network.service.UserService
import tsvetomir.tonchev.findit.utils.datastore.LocalDataStore
import tsvetomir.tonchev.findit.utils.datastore.SessionStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService,
    private val localDataStore: LocalDataStore,
    private val sessionStorage: SessionStorage
) {

    suspend fun login(username: String, password: String) {
        userService.login(LoginRequest(username = username, password = password)).also {
            localDataStore.saveSessionToken(it.token)
        }
        loadUserProfile()
    }

    suspend fun loadUserProfile(): User =
        userService.getUser().also {
            sessionStorage.user = it
        }

    suspend fun register(signupRequest: SignupRequest) {
        userService.register(signupRequest)
    }

    suspend fun logout() {
        userService.logout()
        localDataStore.clearAll()
    }
}