package com.example.eatroom.model.remote

import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.DishRequest
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.RestaurantRequest
import retrofit2.http.*

interface RestaurantApi {
    @GET("/api/v1/Restaurant")
    suspend fun getRestaurantList(): MutableList<Restaurant>

    @POST("/api/v1/Restaurant")
    suspend fun addRestaurant(
        @Body body: RestaurantRequest
    ): Restaurant

    @DELETE("/api/v1/Restaurant/restaurantId")
    suspend fun deleteRestaurant(
        @Query("restautantId") id: Int
    ) : Boolean

    @GET("/api/v1/Restaurants/{restaurantId}/Dish")
    suspend fun getDishesForRestaurant(
        @Path("restaurantId") id : Int
    ): MutableList<Dish>

    @POST("/api/v1/Dish")
    suspend fun addDish(
        @Body body: DishRequest
    ): Dish

    @DELETE("/api/v1/Dish/{dishId}")
    suspend fun deleteDish(
        @Path("dishId") id : Int
    ) : Boolean

    @GET("/api/v1/Dish/{dishId}")
    suspend fun getDish(
        @Path("dishId") id : Int
    ): Dish
}