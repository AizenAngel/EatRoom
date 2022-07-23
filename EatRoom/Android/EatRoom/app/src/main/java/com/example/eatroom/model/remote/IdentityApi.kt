package com.example.eatroom.model.remote

import com.example.eatroom.model.data.*
import retrofit2.http.Body
import retrofit2.http.POST

interface IdentityApi {
    @POST("/api/v1/Authentication/RegisterDeliverer")
    suspend fun registerDeliverer(
        @Body body: RegisterRequest
    )

    @POST("/api/v1/Authentication/RegisterAdministrator")
    suspend fun registerAdmin(
        @Body body: RegisterRequest
    )

    @POST("/api/v1/Authentication/Login")
    suspend fun login(
        @Body body: LoginRequest
    ): AuthResponse
}