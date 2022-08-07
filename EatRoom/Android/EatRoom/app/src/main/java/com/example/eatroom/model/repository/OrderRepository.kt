package com.example.eatroom.model.repository

import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderState

class OrderRepository {

    private val orders = mutableListOf<Order>()

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