package com.example.eatroom.ui.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eatroom.mainActivity
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.coroutineContext

@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalMaterialApi
@Destination
@Composable
fun NewRestaurantScreen(
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel(mainActivity())
) {
    var text by remember { mutableStateOf("") }
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
            viewModel.addNewRestaurant(text, logo)
            navigator.popBackStack()
        }) {
            Text(text = "Add restaurant")
        }
    }
}