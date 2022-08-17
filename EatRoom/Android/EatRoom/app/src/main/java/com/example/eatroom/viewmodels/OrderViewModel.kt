package com.example.eatroom.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.OrderRequest
import com.example.eatroom.model.data.UserType
import com.example.eatroom.model.remote.IdentityApi
import com.example.eatroom.model.remote.OrderApi
import com.example.eatroom.model.remote.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val api: OrderApi,
    private val restaurantApi: RestaurantApi,
    private val identityApi: IdentityApi
) : ViewModel() {

    var order by mutableStateOf<Order?>(null)
    var dishes by mutableStateOf(mutableListOf<Dish>())
    var orders by mutableStateOf(listOf<Order>())
    var userId by mutableStateOf("")

    private fun getDishes() {
        viewModelScope.launch {
            val items = mutableListOf<Dish>()
            order!!.dishes.split(", ").forEach {
                try {
                    items.add(restaurantApi.getDish(it.toInt()))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            dishes = items
        }
    }

    fun getOrders() {
        viewModelScope.launch {
            val response = try {
                api.getOrdersByState(0)
            } catch (e: Exception) {
                e.printStackTrace()
                listOf<Order>()
            }
            orders = response
        }
    }

    fun getMyOrders(username: String, userType: UserType){
        viewModelScope.launch {
            val response = try {
                identityApi.getUser(username!!).id
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
            if (userType == UserType.DRIVER)
                getOrdersForDeliverer(response)
            else
                getOrdersForUser(response)
        }
    }

    private fun getOrdersForUser(id: String){
        viewModelScope.launch {
            val response = try {
                api.getOrdersForUser(id)
            } catch(e: Exception) {
                e.printStackTrace()
                listOf<Order>()
            }
            orders = response
        }
    }

    private fun getOrdersForDeliverer(id: String){
        viewModelScope.launch {
            val response = try {
                api.getOrdersForDeliverer(id)
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
            getDishes()
        }
    }

    fun addOrder(orderRequest: OrderRequest){
        viewModelScope.launch {
            val response = try {
                api.createOrder(orderRequest)
            } catch(e: Exception) {
                e.printStackTrace()
                null
            }
            order = response
        }
    }

    fun changeState() {
        if (order?.state == 0) {
            viewModelScope.launch {
                val response = try {
                    api.putOrder(Order(
                        order!!.id,
                        order!!.userId,
                        order!!.dishes,
                        1,
                        userId,
                        ""
                    ))
                } catch(e: Exception) {
                    e.printStackTrace()
                    null
                }
                order = response
            }
        }
        if (order?.state == 1) {
            viewModelScope.launch {
                val response = try {
                    api.putOrder(Order(
                        order!!.id,
                        order!!.userId,
                        order!!.dishes,
                        2,
                        order!!.delivererId,
                        ""
                    ))
                } catch(e: Exception) {
                    e.printStackTrace()
                    null
                }
                order = response
            }
        }
    }

    fun stateName(state: Int?): String{
        return when(state){
            0 -> "Preparing"
            1 -> "Delivering"
            2 -> "Delivered"
            else -> "Unknown"
        }
    }

    fun getUserId(username: String?){
        viewModelScope.launch {
            val response = try {
                identityApi.getUser(username!!).id
            } catch(e: Exception) {
                e.printStackTrace()
                ""
            }
            userId = response
        }
    }
}