package com.example.eatroom.model.data

data class OrderRequest(
    val userId: String,
    val dishes: String,
    var state: Int,
    val delivererId: String
)
