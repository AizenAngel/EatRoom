package com.example.eatroom.model.data

data class Order(
    val id: Int,
    val userId: String,
    val dishes: String,
    var state: Int,
    val delivererId: String,
    val restaurantName: String
)