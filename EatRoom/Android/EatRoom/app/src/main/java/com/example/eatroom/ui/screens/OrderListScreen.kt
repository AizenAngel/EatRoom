package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.model.data.Order
import com.example.eatroom.ui.screens.destinations.MenuScreenDestination
import com.example.eatroom.ui.screens.destinations.OrderScreenDestination
import com.example.eatroom.viewmodels.OrderViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun OrderListScreen(
    navigator: DestinationsNavigator,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val orders by viewModel.orders

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Order list")
        LazyColumn() {
            items(orders) { order ->
                OrderCard(navigator, order)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun OrderCard(
    navigator: DestinationsNavigator,
    order: Order
) {
    Card(onClick = {
        navigator.navigate(OrderScreenDestination(order.id))
    }) {
        Row {
            Text(text = "${order.restaurantName} ${order.dishes.sumOf { it.price }}din ${order.state}")
        }
    }
}
