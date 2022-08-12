package com.example.eatroom.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.RestaurantRequest
import com.example.eatroom.model.remote.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val api: RestaurantApi
) : ViewModel() {

    var restaurants by mutableStateOf(mutableListOf<Restaurant>())

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

    fun addNewRestaurant(name: String, logo: String?) {
        viewModelScope.launch {
            try {
                api.addRestaurant(RestaurantRequest(name, logo))
                getRestaurants()
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            try {
                api.deleteRestaurant(restaurant.id)
                getRestaurants()
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}