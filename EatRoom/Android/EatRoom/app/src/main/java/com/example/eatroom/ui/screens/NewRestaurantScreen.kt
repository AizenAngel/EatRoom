package com.example.eatroom.ui.screens

import android.os.Build
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eatroom.mainActivity
import com.example.eatroom.viewmodels.RestaurantViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalMaterialApi
@Destination
@Composable
fun NewRestaurantScreen(
    navigator: DestinationsNavigator,
    viewModel: RestaurantViewModel = hiltViewModel(mainActivity())
) {
    var text by remember { mutableStateOf("") }
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
        Text(text = "Add new restaurant", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
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
                viewModel.addNewRestaurant(text, logo)
                navigator.popBackStack()
            }
        ) {
            Icon(Icons.Filled.Check, "")
        }
    }
}