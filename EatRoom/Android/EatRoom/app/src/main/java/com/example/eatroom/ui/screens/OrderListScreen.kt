package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.Order
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.*
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
            Text(text = "Order list", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        else
            Text(text = "My orders", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(orders) { order ->
                OrderCard(userType, username, navigator, order)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
    if (allOrders) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(15.dp),
                onClick = {
                    navigator.navigate(OrderListScreenDestination(username, userType, false))
                }
            ) {
                Text(
                    text = "My orders",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
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
    Card(
        modifier = Modifier.size(width = 280.dp, height = 50.dp),
        backgroundColor = when(order.state) {
            0 -> MaterialTheme.colors.primary
            1 -> MaterialTheme.colors.secondary
            else -> Color.LightGray
        },
        onClick = {
            navigator.navigate(OrderScreenDestination(order.id, userType, username))
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Order number ${order.id} from ${order.restaurantName}")
        }
    }
}
