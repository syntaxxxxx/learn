package com.syntax.learn.data

import com.syntax.learn.data.model.LoginRequest
import com.syntax.learn.data.model.LoginResponse
import com.syntax.learn.data.model.RegisterRequest
import com.syntax.learn.data.model.RegisterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("")
    suspend fun login(@Body req: LoginRequest): LoginResponse

    @POST("")
    suspend fun register(@Body req: RegisterRequest): RegisterResponse

    companion object {
        private const val BASE_URL = ""

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}