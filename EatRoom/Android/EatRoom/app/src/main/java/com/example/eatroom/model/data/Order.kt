package com.example.eatroom.model.data

data class Order(
    val id: Int,
    var restaurantName: String,
    val menus: List<Dish>,
    var state: OrderState
)