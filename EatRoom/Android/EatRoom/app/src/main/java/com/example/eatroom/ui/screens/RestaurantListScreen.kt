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
import androidx.compose.runtime.Composable
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
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.MenuScreenDestination
import com.example.eatroom.ui.screens.destinations.NewRestaurantScreenDestination
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun RestaurantScreen(
    userType: UserType,
    navigator: DestinationsNavigator,
    username: String? = null,
    viewModel: RestaurantViewModel = hiltViewModel(mainActivity()),
    menuViewModel: MenuViewModel = hiltViewModel(mainActivity())
) {
    viewModel.getRestaurants()
    menuViewModel.deleteBasket(username)
    val restaurants = viewModel.restaurants

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Restaurant list", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(restaurants) { restaurant ->
                RestaurantCard(navigator, restaurant, userType, username)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(15.dp),
            onClick = {
                if (userType == UserType.OWNER)
                    navigator.navigate(NewRestaurantScreenDestination())
                if (userType == UserType.USER)
                    navigator.navigate(OrderListScreenDestination(username!!, userType, false))
            }
        ) {
            if (userType == UserType.OWNER)
                Icon(Icons.Filled.Add, "")
            if (userType == UserType.USER)
                Text(
                    text = "My orders",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun RestaurantCard(
    navigator: DestinationsNavigator,
    restaurant: Restaurant,
    userType: UserType,
    username: String?
) {
    Card(
        modifier = Modifier.size(width = 280.dp, height = 120.dp),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            navigator.navigate(MenuScreenDestination(restaurant, userType, username))
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (restaurant.logoFile != null) {
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
                                    restaurant.logoFile
                                        .substring(
                                            restaurant.logoFile
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
            Text(
                text = restaurant.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}