package com.example.eatroom.ui.screens

import android.util.Base64
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.*
import com.example.eatroom.ui.screens.destinations.BasketScreenDestination
import com.example.eatroom.ui.screens.destinations.NewDishScreenDestination
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun MenuScreen(
    restaurant: Restaurant,
    userType: UserType,
    username: String? = null,
    navigator: DestinationsNavigator,
    viewModel: MenuViewModel = hiltViewModel(mainActivity()),
    restaurantViewModel: RestaurantViewModel = hiltViewModel(mainActivity())
) {
    viewModel.getDishes(restaurant.id)
    viewModel.getBasket(username)

    val dishes = viewModel.dishes
    val basket = viewModel.basket

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu list for ${restaurant.name}")
        LazyColumn() {
            items(dishes) { dish ->
                DishCard(dish, userType, restaurant, username)
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
                    restaurantViewModel.deleteRestaurant(restaurant)
                    navigator.popBackStack()
                }) {
                    Text(text = "Delete restaurant")
                }
            }
        }
        if (userType == UserType.USER) {
            Button(onClick = {
                navigator.navigate(BasketScreenDestination(restaurant, username))
            }) {
                Text(text = "Basket ${basket.totalPrice}")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DishCard(
    dish: Dish,
    userType: UserType,
    restaurant: Restaurant,
    username: String?,
    viewModel: MenuViewModel = hiltViewModel(mainActivity())
) {
    Card() {
        Row() {
            Column() {
                Text(dish.name)
                if (dish.imageFile != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                Base64.decode(
                                    dish.imageFile
                                        .substring(dish.imageFile
                                        .indexOf(",")  + 1),
                                Base64.DEFAULT)
                            )
                            .crossfade(true)
                            .build(),
                        contentDescription = null
                    )
                }
                Text(dish.price.toString() + "din")
            }
            if (userType == UserType.USER){
                Button(onClick = {
                    viewModel.addItemToBasket(BasketItem(dish.name, dish.price), username)
                }) {
                    Text(text = "Add to basket")
                }
            }
            if (userType == UserType.OWNER){
                Button(onClick = {
                    viewModel.deleteDish(dish, restaurant)
                }) {
                    Text(text = "Delete item")
                }
            }
        }
    }
}
