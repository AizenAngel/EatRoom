package com.example.eatroom.model.remote

import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.RestaurantRequest
import retrofit2.http.*

interface RestaurantApi {
    @GET("/api/v1/Restaurant")
    suspend fun getRestaurantList(): MutableList<Restaurant>

    @GET("/api/v1/Restaurant")
    suspend fun getRestaurant(
        @Query("restaurantId") id: Int
    ): Restaurant

    @POST("/api/v1/Restaurant")
    suspend fun addRestaurant(
        @Body body: RestaurantRequest
    ): Restaurant
}