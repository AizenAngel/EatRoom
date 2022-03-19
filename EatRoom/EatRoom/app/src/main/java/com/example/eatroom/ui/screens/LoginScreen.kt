package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select user type")
        Button(
            onClick = {
                navigator.navigate(RestaurantScreenDestination())
            }
        ) {
            Text(text = "User")
        }
        Button(
            onClick = {
                navigator.navigate(RestaurantScreenDestination())
            }
        ) {
            Text(text = "Owner")
        }
        Button(
            onClick = {
                navigator.navigate(OrderListScreenDestination())
            }
        ) {
            Text(text = "Driver")
        }
    }
}