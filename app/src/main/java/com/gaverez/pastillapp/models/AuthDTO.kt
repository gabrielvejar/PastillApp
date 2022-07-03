package com.gaverez.pastillapp.models

data class LoginPayloadDTO (
    val identifier: String,
    val password: String
)

data class UserDTO (
    val id: Long,
    val username: String
)

data class LoginResponseDTO (
    val jwt: String,
    val user: UserDTO
)

data class RegisterPayloadDTO (
    val username: String,
    val email: String,
    val password: String
)
