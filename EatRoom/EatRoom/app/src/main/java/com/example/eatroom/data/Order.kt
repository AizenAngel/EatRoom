package com.example.eatroom.data

data class Order(
    val restaurantName: String,
    val dishes: List<Dish>
)