package com.example.eatroom.model.repository

import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderState

class OrderRepository {

    private val orders = mutableListOf(
        Order(1, "Picerija", listOf(Dish("pica", 1000)), OrderState.PREPARING),
        Order(2, "Rostilj", listOf(Dish("cevapi", 500)), OrderState.PREPARING)
    )

    fun getOrders(): MutableList<Order> {
        return orders
    }

    fun getOrderById(id: Int): Order {
        return orders.first { it.id == id }
    }

    fun addOrder(order: Order){
        orders.add(order)
    }
}