package com.example.eatroom.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.eatroom.model.data.Restaurant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MenuScreen(
    restaurant: Restaurant,
    navigator: DestinationsNavigator
) {
    Text(text = "Menu list for ${restaurant.name}")
}