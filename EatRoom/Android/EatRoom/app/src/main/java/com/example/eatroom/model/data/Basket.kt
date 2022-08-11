package com.example.eatroom.model.data

data class Basket(
    val username: String,
    val totalPrice: Int,
    val items: MutableList<BasketItem>
)
