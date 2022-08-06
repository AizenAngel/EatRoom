package com.example.eatroom.model.data

data class DishRequest(
    val name: String,
    val imageFile: String?,
    val price: Int,
    val restaurantId: Int
)
