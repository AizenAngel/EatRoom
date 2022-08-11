package com.example.eatroom.model.data

data class BasketRequest(
    val username: String,
    val items: List<BasketItem>
)
