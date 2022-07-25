package tsvetomir.tonchev.findit.domain.repository

import tsvetomir.tonchev.findit.data.network.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(val userService: UserService) {

    suspend fun login(username: String, password: String) {

    }
}