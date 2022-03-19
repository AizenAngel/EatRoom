package com.example.eatroom.model.data

data class Order(
    val restaurantName: String,
    val dishes: List<Dish>
)