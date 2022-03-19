package com.example.eatroom.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RestaurantScreen(
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    Text(text = "Restaurant list")
}