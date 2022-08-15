package com.example.eatroom.model.remote

import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderRequest
import retrofit2.http.*

interface OrderApi {

    @GET("/api/Order/{state}")
    suspend fun getOrdersByState(
        @Path("state") state : Int
    ): List<Order>

    @POST("/api/Order")
    suspend fun createOrder(
        @Body body: OrderRequest
    ): Order

    @PUT("/api/Order")
    suspend fun putOrder(
        @Body body: Order
    ): Order

    @GET("/api/Order/deliverer/{delivererId}")
    suspend fun getOrdersForDeliverer(
        @Path("delivererId") id : String
    ): List<Order>

    @GET("/api/Order/order/{orderId}")
    suspend fun getOrderById(
        @Path("orderId") id : Int
    ): Order
}