package com.example.eatroom.model.repository

import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant

class RestaurantRepository {
    private val restaurants = mutableListOf<Restaurant>(
        Restaurant(
            "Picerija",
            mutableListOf(
                Dish("Kapricoza", 800),
                Dish("Madjarica", 1000)
            )
        ),
        Restaurant(
            "Rostilj",
            mutableListOf(
                Dish("Cevapi", 250),
                Dish("Pljeskavica", 250)
            )
        )
    )

    fun getRestaurants() : List<Restaurant> {
        return restaurants
    }

    fun addRestaurant(restaurant: Restaurant) {
        restaurants.add(restaurant)
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        restaurants.remove(restaurant)
    }
}