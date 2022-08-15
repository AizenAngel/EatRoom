package com.example.eatroom.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderRequest
import com.example.eatroom.model.data.OrderState
import com.example.eatroom.model.remote.OrderApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val api: OrderApi
) : ViewModel() {

    var order by mutableStateOf<Order?>(null)
    var orders by mutableStateOf(listOf<Order>())

    fun getOrders(){
        viewModelScope.launch {
            val response = try {
                api.getOrdersByState(0)
            } catch(e: Exception) {
                e.printStackTrace()
                listOf<Order>()
            }
            orders = response
        }
    }

    fun setOrder(id: Int){
        viewModelScope.launch {
            val response = try {
                api.getOrderById(id)
            } catch(e: Exception) {
                e.printStackTrace()
                null
            }
            order = response
        }
    }

    fun addOrder(order: OrderRequest){
        viewModelScope.launch {
            try {
                api.createOrder(order)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun changeState(): OrderState {
        if (order?.state == OrderState.PREPARING)
            order?.state = OrderState.DELIVERING
        else
            order?.state = OrderState.DELIVERED
        return order?.state ?: OrderState.PREPARING
    }
}