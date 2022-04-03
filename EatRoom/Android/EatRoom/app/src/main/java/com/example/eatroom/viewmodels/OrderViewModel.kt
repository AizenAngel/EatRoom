package com.example.eatroom.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderState
import com.example.eatroom.model.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

    var order = mutableStateOf<Order?>(null)
    var orders = mutableStateOf(repository.getOrders())

    fun setOrder(id: Int){
        order.value = repository.getOrderById(id)
    }

    fun addOrder(order: Order){
        repository.addOrder(order)
    }

    fun changeState(): OrderState {
        if (order.value?.state == OrderState.PREPARING)
            order.value?.state = OrderState.DELIVERING
        else
            order.value?.state = OrderState.DELIVERED
        return order.value?.state ?: OrderState.PREPARING
    }
}