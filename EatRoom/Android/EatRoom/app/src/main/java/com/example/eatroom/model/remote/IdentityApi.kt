package com.example.eatroom.model.remote

import com.example.eatroom.model.data.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IdentityApi {
    @POST("/api/v1/Authentication/RegisterDeliverer")
    suspend fun registerDeliverer(
        @Body body: RegisterRequest
    )

    @POST("/api/v1/Authentication/RegisterAdministrator")
    suspend fun registerAdmin(
        @Body body: RegisterRequest
    )

    @POST("/api/v1/Authentication/RegisterCustomer")
    suspend fun registerCustomer(
        @Body body: RegisterRequest
    )

    @POST("/api/v1/Authentication/Login")
    suspend fun login(
        @Body body: LoginRequest
    ): AuthResponse

    @GET("/api/v1/User/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): User
}