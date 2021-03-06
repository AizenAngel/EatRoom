package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.RegisterScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select user type")
        Button(
            onClick = {
                navigator.navigate(RestaurantScreenDestination(UserType.USER))
            }
        ) {
            Text(text = "User")
        }
        Button(
            onClick = {
                navigator.navigate(RestaurantScreenDestination(UserType.OWNER))
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
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Button(
            onClick = {
                navigator.navigate(OrderListScreenDestination())
            }
        ) {
            Text(text = "Login")
        }
        Button(
            onClick = {
                navigator.navigate(RegisterScreenDestination())
            }
        ) {
            Text(text = "Register")
        }
    }
}