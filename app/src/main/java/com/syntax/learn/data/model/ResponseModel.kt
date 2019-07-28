package com.syntax.learn.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    val avatar: String?,
    val linkedin: String,
    @SerializedName("git_repo")
    val gitRepo: String,
    val blog: String,
    val facebook: String,
    val youtube: String,
    val twitter: String,
    val instagram: String,
    @SerializedName("telegram_id")
    val telegramId: String,
    @SerializedName("curriculum_vitae")
    val curriculumVitae: String?,
    @SerializedName("has_profile")
    val hasProfile: Boolean
)

data class LoginResponse(
    val refresh: String,
    val access: String,
    val user: UserResponse
)
data class RegisterResponse(
    val success : String,
    val failed : String
)

data class ErrorResponse(
    val detail: String,
    @SerializedName("error_message")
    val errorMessage: String,
    @SerializedName("error_code")
    val errorCode: String
)