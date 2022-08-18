package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.*
import com.example.eatroom.ui.screens.destinations.OrderScreenDestination
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.OrderViewModel
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
        Text(text = "Basket", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(basket.items) { dish ->
                BasketItemCard(dish, username)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            modifier = Modifier.size(width = 280.dp, height = 50.dp),
            onClick = {
                orderViewModel.addOrder(OrderRequest(
                    userId,
                    basket.items.map { it.id }.joinToString(),
                    0,
                    "-1")
                )
                viewModel.deleteBasket(username)
            }
        ) {
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
    Card(
        modifier = Modifier.size(width = 280.dp, height = 120.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(dish.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(dish.price.toString() + "din")
                FloatingActionButton(
                    modifier = Modifier.size(width = 40.dp, height = 40.dp),
                    backgroundColor = Color.Red,
                    contentColor = Color.White,
                    onClick = {
                        viewModel.deleteBasketItem(dish, username)
                    }
                ) {
                    Icon(Icons.Filled.Delete, "")
                }
            }
        }
    }
}
