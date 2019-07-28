package com.syntax.learn.data.model

data class LoginRequest(
    val username: String,
    val password: String
)
data class RegisterRequest(
    val email : String,
    val username : String,
    val password1 : String,
    val password2 : String
)