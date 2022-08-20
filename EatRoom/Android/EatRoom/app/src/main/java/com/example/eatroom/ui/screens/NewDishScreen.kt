package com.example.eatroom.ui.screens

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    restaurantId: Int,
    viewModel: MenuViewModel = hiltViewModel(mainActivity())
) {
    var text by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var logo by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { result ->
        val item = context.contentResolver.openInputStream(result!!)
        val bytes = item?.readBytes()
        logo = "data:image/jpeg;base64," + Base64.encodeToString(
            bytes,
            Base64.DEFAULT
        )
        item?.close()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add new dish", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier.size(width = 280.dp, height = 50.dp),
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text(text = "Pick image")
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (logo != null) {
            AsyncImage(
                modifier = Modifier.size(width = 280.dp, height = 280.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Base64.decode(
                        logo!!.substring(logo!!.indexOf(",") + 1),
                        Base64.DEFAULT)
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(15.dp),
            onClick = {
                viewModel.addDishToRestaurant(
                    DishRequest(
                        text,
                        logo,
                        price.toInt(),
                        restaurantId
                    )
                )
                navigator.popBackStack()
            }
        ) {
            Icon(Icons.Filled.Check, "")
        }
    }
}