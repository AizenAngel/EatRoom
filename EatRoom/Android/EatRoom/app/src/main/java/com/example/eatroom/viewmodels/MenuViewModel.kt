package com.example.eatroom.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.DishRequest
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.remote.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val api: RestaurantApi
) : ViewModel() {

    var dishes by mutableStateOf(mutableListOf<Dish>())
    var basket by mutableStateOf(mutableListOf<Dish>())

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

    fun clearBasket() {
        basket.clear()
    }

    fun addItemToBasket(dish: Dish) {
        basket.add(dish)
    }

    fun basketPrice(): Int {
        return basket.sumOf { it.price } ?: 0
    }

    fun deleteBasketItem(dish: Dish) {
        basket.remove(dish)
    }
}