package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.model.data.Order
import com.example.eatroom.viewmodels.OrderViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OrderScreen(
    id: Int,
    viewModel: OrderViewModel = hiltViewModel()
) {
    viewModel.setOrder(id)
    val order by viewModel.order
    var orderState = remember{ mutableStateOf(order?.state) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Order info")
        order?.let { it ->
            Text(text = it.restaurantName)
            Text(text = "Price ${it.dishes.sumOf { it.price }}din")
        }
        Text(text = "${orderState.value} order")
        Button(onClick = {
            orderState.value = viewModel.changeState()
        }) {
            Text(text = "Next state")
        }
    }
}