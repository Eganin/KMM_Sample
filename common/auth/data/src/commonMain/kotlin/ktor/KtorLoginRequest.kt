package ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginRequest(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)
