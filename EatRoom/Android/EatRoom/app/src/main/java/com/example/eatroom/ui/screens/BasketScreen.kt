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
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.*
import com.example.eatroom.ui.screens.destinations.OrderScreenDestination
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.OrderViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun BasketScreen(
    username: String? = null,
    userType: UserType,
    navigator: DestinationsNavigator,
    viewModel: MenuViewModel = hiltViewModel(mainActivity()),
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    viewModel.getBasket(username)
    viewModel.getUserId(username)
    val basket = viewModel.basket
    val userId = viewModel.userId
    if (orderViewModel.order != null) {
        navigator.navigate(OrderScreenDestination(orderViewModel.order!!.id, userType, username!!))
        orderViewModel.order = null
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Basket")
        LazyColumn() {
            items(basket.items) { dish ->
                BasketItemCard(dish, username)
            }
        }
        Button(onClick = {
            orderViewModel.addOrder(OrderRequest(
                userId,
                basket.items.map { it.id }.joinToString(),
                0,
                "-1")
            )
            viewModel.deleteBasket(username)
        }) {
            Text(text = "Order for ${basket.totalPrice}din")
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BasketItemCard(
    dish: BasketItem,
    username: String?,
    viewModel: MenuViewModel = hiltViewModel(mainActivity())
) {
    Card() {
        Row() {
            Column() {
                Text(dish.name)
                Text(dish.price.toString() + "din")
            }

            Button(onClick = {
                viewModel.deleteBasketItem(dish, username)
            }) {
                Text(text = "Delete item")
            }
        }
    }
}
