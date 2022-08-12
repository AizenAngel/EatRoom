package com.example.eatroom.ui.screens

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eatroom.mainActivity
import com.example.eatroom.model.data.DishRequest
import com.example.eatroom.model.data.Restaurant
import com.example.eatroom.viewmodels.MenuViewModel
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination
@Composable
fun NewDishScreen(
    navigator: DestinationsNavigator,
    viewModel: MenuViewModel = hiltViewModel(mainActivity()),
    restaurant: Restaurant
) {
    var text by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var logo by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

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
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Pick image")
        }
        imageUri?.let {
            logo = "data:image/jpeg;base64," + Base64.encodeToString(
                LocalContext.current.contentResolver.openInputStream(imageUri!!)?.readBytes(),
                Base64.DEFAULT
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }
        Button(onClick = {
            viewModel.addDishToRestaurant(
                DishRequest(
                text,
                logo,
                price.toInt(),
                restaurant.id
            )
            )
            navigator.popBackStack()
        }) {
            Text(text = "Add dish")
        }
    }
}