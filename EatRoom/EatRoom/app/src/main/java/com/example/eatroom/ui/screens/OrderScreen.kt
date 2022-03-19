package com.example.eatroom.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.viewmodels.OrderViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun OrderScreen(
    navigator: DestinationsNavigator,
    viewModel: OrderViewModel = hiltViewModel()
) {
    Text(text = "Order info")
}