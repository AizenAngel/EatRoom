package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun NewDishScreen(
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel(),
    restaurant: Restaurant
) {
    var text by remember { mutableStateOf("Hello") }
    var price by remember { mutableStateOf("") }
    Column() {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add dish")
        }
    }
}