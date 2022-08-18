package com.example.eatroom.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.RegisterScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.example.eatroom.ui.theme.EatRoomTheme
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
        UserType.DRIVER -> {
            navigator.navigate(OrderListScreenDestination(username, UserType.DRIVER, true))
            viewModel.userType = null
        }
        UserType.USER -> {
            navigator.navigate(RestaurantScreenDestination(UserType.USER, username))
            viewModel.userType = null
        }
        UserType.OWNER -> {
            navigator.navigate(RestaurantScreenDestination(UserType.OWNER))
            viewModel.userType = null
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "EatRoom",
            fontSize = 50.sp,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Login", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier.size(width = 280.dp, height = 50.dp),
            onClick = {
                viewModel.login(username, password)
            }
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.size(width = 280.dp, height = 50.dp),
            onClick = {
                navigator.navigate(RegisterScreenDestination())
            }
        ) {
            Text(text = "Register")
        }
    }
}