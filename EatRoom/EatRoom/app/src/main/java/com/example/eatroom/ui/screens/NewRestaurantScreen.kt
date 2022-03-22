package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun NewRestaurantScreen(
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    Column() {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
        )
        Button(onClick = {
            viewModel.addNewRestaurant(text)
            navigator.popBackStack()
        }) {
            Text(text = "Add restaurant")
        }
    }
}