package com.example.eatroom.ui.screens

import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Text(
            text = "${viewModel.stateName(order?.state)} order from ${order?.restaurantName}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(dishes) { dish ->
                OrderDishCard(dish)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Text(text = "Total price ${viewModel.dishes.sumOf { it.price }} din",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (userType == UserType.DRIVER) {
            if (order?.state == 0) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier.size(width = 280.dp, height = 50.dp),
                    onClick = {
                        viewModel.changeState()
                    }
                ) {
                    Text(text = "Take this order")
                }
            }
            if (order?.state == 1) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier.size(width = 280.dp, height = 50.dp),
                    onClick = {
                        viewModel.changeState()
                    }
                ) {
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
    Card(
        modifier = Modifier.size(width = 280.dp, height = 120.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (dish.imageFile != null) {
                Card(
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(width = 120.dp, height = 120.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                Base64.decode(
                                    dish.imageFile
                                        .substring(
                                            dish.imageFile
                                                .indexOf(",") + 1
                                        ),
                                    Base64.DEFAULT
                                )
                            )
                            .crossfade(true)
                            .build(),
                        contentDescription = null
                    )
                }
            }
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
                }
            }
        }
    }
}