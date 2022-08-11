package com.example.eatroom.model.remote

import com.example.eatroom.model.data.Basket
import com.example.eatroom.model.data.BasketRequest
import retrofit2.http.*

interface BasketApi {
    @GET("/api/v1/Basket/{username}")
    suspend fun getBasket(
        @Path("username") username : String
    ): Basket

    @PUT("/api/v1/Basket")
    suspend fun putBasket(
        @Body body: BasketRequest
    ): Basket

    @DELETE("/api/v1/Basket/{username}")
    suspend fun deleteBasket(
        @Path("username") username : String
    ) : Boolean
}