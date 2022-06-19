package com.example.eatroom.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    var restaurants = mutableStateOf(mutableListOf<Restaurant>())
    val menus = mutableStateListOf<Dish>()
    val basket = mutableStateListOf<Dish>()

    init {
        viewModelScope.launch {
            restaurants.value = repository.getRestaurants()
        }
    }

    fun getRestaurants() {
        viewModelScope.launch {
            restaurants.value = repository.getRestaurants()
        }
    }

    fun addNewRestaurant(name: String) {
        viewModelScope.launch {
            repository.addRestaurant(name)
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            repository.deleteRestaurant(restaurant.id)
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