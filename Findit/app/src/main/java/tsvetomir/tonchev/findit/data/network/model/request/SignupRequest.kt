package tsvetomir.tonchev.findit.data.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupRequest(
    @Json(name = "username")
    val username: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "password")
    val password: String
)