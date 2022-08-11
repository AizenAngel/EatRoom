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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.RegisterScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.example.eatroom.viewmodels.LoginViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType = viewModel.userType
    when (userType) {
        UserType.DRIVER -> navigator.navigate(OrderListScreenDestination())
        UserType.USER -> navigator.navigate(RestaurantScreenDestination(UserType.USER, username))
        UserType.OWNER -> navigator.navigate(RestaurantScreenDestination(UserType.OWNER))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                viewModel.login(username, password)
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