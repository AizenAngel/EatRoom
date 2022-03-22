package com.example.eatroom.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    val restaurants = mutableStateOf(repository.getRestaurants())
    val dishes = mutableStateListOf<Dish>()

    fun addNewRestaurant(name: String) {
        repository.addRestaurant(Restaurant(name, mutableListOf()))
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        repository.deleteRestaurant(restaurant)
    }

    fun setDish(restaurant: Restaurant) {
        dishes.clear()
        dishes.addAll(repository.getDishes(restaurant))
    }

    fun addDishToRestaurant(restaurant: Restaurant, name: String, price: Int) {
        repository.addDish(restaurant, Dish(name, price))
    }

    fun deleteDish(restaurant: Restaurant, dish: Dish){
        repository.deleteDish(restaurant, dish)
        setDish(restaurant)
    }
}