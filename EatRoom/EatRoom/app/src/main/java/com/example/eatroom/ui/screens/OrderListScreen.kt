package com.example.eatroom.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun OrderListScreen(
    navigator: DestinationsNavigator
) {
    Text(text = "Order list")
}