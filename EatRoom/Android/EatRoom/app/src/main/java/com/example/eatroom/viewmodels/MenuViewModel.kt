package com.example.eatroom.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.*
import com.example.eatroom.model.remote.BasketApi
import com.example.eatroom.model.remote.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val api: RestaurantApi,
    private val basketApi: BasketApi
) : ViewModel() {

    var dishes by mutableStateOf(mutableListOf<Dish>())
    var basket by mutableStateOf(Basket("", 0, mutableListOf()))

    fun getDishes(restaurant: Restaurant) {
        viewModelScope.launch {
            val response = try {
                api.getDishesForRestaurant(restaurant.id)
            } catch(e: Exception) {
                e.printStackTrace()
                mutableListOf<Dish>()
            }
            dishes = response
        }
    }

    fun addDishToRestaurant(request: DishRequest) {
        viewModelScope.launch {
            try {
                api.addDish(request)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteDish(dish: Dish, restaurant: Restaurant){
        viewModelScope.launch {
            try {
                api.deleteDish(dish.id)
                getDishes(restaurant)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getBasket(username: String?) {
        if (username != null) {
            viewModelScope.launch {
                val response = try {
                    basketApi.getBasket(username)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Basket("", 0, mutableListOf())
                }
                basket = response
            }
        }
    }

    private fun putBasket(username: String?) {
        if (username != null) {
            viewModelScope.launch {
                val response = try {
                    basketApi.putBasket(BasketRequest(username, basket.items))
                } catch (e: Exception) {
                    e.printStackTrace()
                    Basket(username, 0, mutableListOf())
                }
                basket = response
            }
        }
    }

    fun deleteBasket(username: String?) {
        if (username != null) {
            viewModelScope.launch {
                try {
                    basketApi.deleteBasket(username)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun addItemToBasket(dish: BasketItem, username: String?) {
        basket.items.add(dish)
        putBasket(username)
    }

    fun deleteBasketItem(dish: BasketItem, username: String?) {
        basket.items.remove(dish)
        putBasket(username)
    }
}