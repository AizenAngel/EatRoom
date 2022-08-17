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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.BasketScreenDestination
import com.example.eatroom.ui.screens.destinations.MenuScreenDestination
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.OrderScreenDestination
import com.example.eatroom.viewmodels.OrderViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun OrderListScreen(
    username: String,
    userType: UserType,
    navigator: DestinationsNavigator,
    allOrders: Boolean,
    viewModel: OrderViewModel = hiltViewModel(mainActivity())
) {
    if (allOrders)
        viewModel.getOrders()
    else
        viewModel.getMyOrders(username, userType)

    val orders = viewModel.orders

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (allOrders)
            Text(text = "Order list")
        else
            Text(text = "My orders")
        LazyColumn() {
            items(orders) { order ->
                OrderCard(userType, username, navigator, order)
            }
        }
        if (allOrders) {
            Button(onClick = {
                navigator.navigate(OrderListScreenDestination(username, userType, false))
            }) {
                Text(text = "My orders")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun OrderCard(
    userType: UserType,
    username: String,
    navigator: DestinationsNavigator,
    order: Order
) {
    Card(onClick = {
        navigator.navigate(OrderScreenDestination(order.id, userType, username))
    }) {
        Row {
            Text(text = "Order number ${order.id} from ${order.restaurantName}")
        }
    }
}
