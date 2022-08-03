package tsvetomir.tonchev.findit.data.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tsvetomir.tonchev.findit.data.network.model.request.LoginRequest
import tsvetomir.tonchev.findit.data.network.model.request.SignupRequest
import tsvetomir.tonchev.findit.data.network.model.response.LoginResponse
import tsvetomir.tonchev.findit.data.network.model.response.User

interface UserService {
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body loginRequest: SignupRequest): Response<Unit>

    @POST("api/auth/signout")
    suspend fun logout(): Response<Unit>

    @GET("api/user/profile")
    suspend fun getUser(): User
}