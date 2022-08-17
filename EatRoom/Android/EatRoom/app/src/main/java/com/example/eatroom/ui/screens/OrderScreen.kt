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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.*
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.OrderViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun OrderScreen(
    id: Int,
    userType: UserType,
    username: String,
    viewModel: OrderViewModel = hiltViewModel(mainActivity())
) {
    viewModel.setOrder(id)
    viewModel.getUserId(username)
    val order = viewModel.order
    val dishes = viewModel.dishes

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Order info")
        Text(text = "Restaurant name ${order?.restaurantName}")
        LazyColumn() {
            items(dishes) { dish ->
                OrderDishCard(dish)
            }
        }
        Text(text = "Total price ${viewModel.dishes.sumOf { it.price }} din")
        Text(text = "${viewModel.stateName(order?.state)} order")
        if (userType == UserType.DRIVER) {
            if (order?.state == 0) {
                Button(onClick = {
                    viewModel.changeState()
                }) {
                    Text(text = "Take this order")
                }
            }
            if (order?.state == 1) {
                Button(onClick = {
                    viewModel.changeState()
                }) {
                    Text(text = "Deliver this order")
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun OrderDishCard(
    dish: Dish
) {
    Card() {
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
    }
}