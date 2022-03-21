package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.MenuScreenDestination
import com.example.eatroom.ui.screens.destinations.NewRestaurantScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun RestaurantScreen(
    userType: UserType,
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurants by viewModel.restaurants

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Restaurant list")
        LazyColumn() {
            items(restaurants) { restaurant ->
                RestaurantCard(navigator, restaurant, userType)
            }
        }
        if (userType == UserType.OWNER){
            Button(onClick = {
                navigator.navigate(NewRestaurantScreenDestination())
            }) {
                Text(text = "Add Restaurant")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun RestaurantCard(
    navigator: DestinationsNavigator,
    restaurant: Restaurant,
    userType: UserType
) {
    Card(
        onClick = {
            navigator.navigate(MenuScreenDestination(restaurant, userType))
        }
    ) {
        Text(text = restaurant.name)
    }
}