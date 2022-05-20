package com.example.eatroom.model.repository

import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.RestaurantRequest
import com.example.eatroom.model.remote.RestaurantApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi
) {
    suspend fun getRestaurants(): MutableList<Restaurant> {
        val response = try {
            api.getRestaurantList()
        } catch(e: Exception) {
            e.printStackTrace()
            mutableListOf<Restaurant>()
        }
        return response
    }

    suspend fun getRestaurantById(id: Int): Restaurant {
        val response = try {
            api.getRestaurant(id)
        } catch(e: Exception) {
            e.printStackTrace()
            Restaurant("", -1)
        }
        return response
    }

    suspend fun addRestaurant(name: String): Restaurant {
        val response = try {
            api.addRestaurant(RestaurantRequest(name))
        } catch(e: Exception) {
            e.printStackTrace()
            Restaurant("", -1)
        }
        return response
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        //restaurants.remove(restaurants.first { it.name == restaurant.name })
    }

    fun getDishes(restaurant: Restaurant): List<Dish> {
        return mutableListOf() //restaurants.first { it.name == restaurant.name }.menus[0].dishes
    }

    fun addDish(restaurant: Restaurant, dish: Dish) {
        //restaurants.first { it.name == restaurant.name }.menus[0].dishes.add(dish)
    }

    fun deleteDish(restaurant: Restaurant, dish: Dish) {
        //restaurants.first { it.name == restaurant.name }.menus[0].dishes.remove(dish)
    }

    private val basket = mutableListOf<Dish>()

    fun getBasket(): List<Dish> {
        return basket
    }

    fun clearBasket() {
        basket.clear()
    }

    fun addToBasket(dish: Dish) {
        basket.add(dish)
    }

    fun deleteBasketItem(dish: Dish) {
        basket.remove(dish)
    }
}