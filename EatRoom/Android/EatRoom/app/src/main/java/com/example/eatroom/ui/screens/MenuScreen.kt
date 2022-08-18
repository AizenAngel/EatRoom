package com.example.eatroom.ui.screens

import android.util.Base64
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Text(text = "Menu for ${restaurant.name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(dishes) { dish ->
                DishCard(dish, userType, restaurant, username)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        if (userType == UserType.USER) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                modifier = Modifier.size(width = 280.dp, height = 50.dp),
                onClick = {
                    navigator.navigate(BasketScreenDestination(username, userType))
                }
            ) {
                Text(text = "Basket ${basket.totalPrice}din")
            }
        }
    }
    if (userType == UserType.OWNER) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(end = 15.dp),
                onClick = {
                    navigator.navigate(NewDishScreenDestination(restaurant))
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
            FloatingActionButton(
                backgroundColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier.padding(15.dp),
                onClick = {
                    restaurantViewModel.deleteRestaurant(restaurant)
                    navigator.popBackStack()
                }
            ) {
                Icon(Icons.Filled.Delete, "")
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
                    if (userType == UserType.USER){
                        FloatingActionButton(
                            modifier = Modifier.size(width = 40.dp, height = 40.dp),
                            backgroundColor = MaterialTheme.colors.secondary,
                            onClick = {
                                viewModel.addItemToBasket(
                                    BasketItem(
                                        dish.name,
                                        dish.price,
                                        dish.id
                                    ),
                                    username
                                )
                            }
                        ) {
                            Icon(Icons.Filled.Add, "")
                        }
                    }
                    if (userType == UserType.OWNER){
                        FloatingActionButton(
                            modifier = Modifier.size(width = 40.dp, height = 40.dp),
                            backgroundColor = Color.Red,
                            contentColor = Color.White,
                            onClick = {
                                viewModel.deleteDish(dish, restaurant)
                            }
                        ) {
                            Icon(Icons.Filled.Delete, "")
                        }
                    }
                }
            }
        }
    }
}
