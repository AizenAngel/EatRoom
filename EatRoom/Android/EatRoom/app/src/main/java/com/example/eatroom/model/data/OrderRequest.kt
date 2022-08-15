package com.example.eatroom.model.data

data class OrderRequest(
    val userId: String,
    val dishes: List<String>,
    var state: OrderState
)
