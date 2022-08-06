package com.example.eatroom.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.RestaurantRequest
import com.example.eatroom.model.remote.RestaurantApi
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository,
    private val api: RestaurantApi
) : ViewModel() {

    var restaurants by mutableStateOf(mutableListOf<Restaurant>())
    val menus = mutableStateListOf<Dish>()
    val basket = mutableStateListOf<Dish>()

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        viewModelScope.launch {
            val response = try {
                api.getRestaurantList()
            } catch(e: Exception) {
                e.printStackTrace()
                mutableListOf<Restaurant>()
            }
            restaurants = response
        }
    }

    fun addNewRestaurant(name: String) {
        viewModelScope.launch {
            try {
                api.addRestaurant(RestaurantRequest(name))
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            try {
                api.deleteRestaurant(restaurant.id)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setDish(restaurant: Restaurant) {
        menus.clear()
        menus.addAll(repository.getDishes(restaurant))
    }

    fun addDishToRestaurant(restaurant: Restaurant, name: String, price: Int) {
        repository.addDish(restaurant, Dish(name, price))
    }

    fun deleteDish(restaurant: Restaurant, dish: Dish){
        repository.deleteDish(restaurant, dish)
        setDish(restaurant)
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