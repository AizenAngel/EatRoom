package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.eatroom.model.data.Dish
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.NewDishScreenDestination
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun MenuScreen(
    restaurant: Restaurant,
    navigator: DestinationsNavigator,
    userType: UserType,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    viewModel.setDish(restaurant)
    val dishes = viewModel.dishes
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu list for ${restaurant.name}")
        LazyColumn() {
            items(dishes) { dish ->
                DishCard(restaurant, dish, userType)
            }
        }
        if (userType == UserType.OWNER) {
            Row() {
                Button(onClick = {
                    navigator.navigate(NewDishScreenDestination(restaurant))
                }) {
                    Text(text = "Add menu item")
                }
                Button(onClick = {
                    viewModel.deleteRestaurant(restaurant)
                    navigator.popBackStack()
                }) {
                    Text(text = "Delete restaurant")
                }
            }
        }
    }
}

@Composable
fun DishCard(
    restaurant: Restaurant,
    dish: Dish,
    userType: UserType,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    Card() {
        Row() {
            Column() {
                Text(dish.name)
                Text(dish.price.toString() + "din")
            }
            if (userType == UserType.USER){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Add to basket")
                }
            }
            if (userType == UserType.OWNER){
                Button(onClick = {
                    viewModel.deleteDish(restaurant, dish)
                }) {
                    Text(text = "Delete item")
                }
            }
        }
    }
}
