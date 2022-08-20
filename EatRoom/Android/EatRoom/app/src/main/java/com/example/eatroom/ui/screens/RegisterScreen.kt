package com.example.eatroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eatroom.model.data.RegisterRequest
import com.example.eatroom.model.data.UserType
import com.example.eatroom.ui.screens.destinations.OrderListScreenDestination
import com.example.eatroom.ui.screens.destinations.RestaurantScreenDestination
import com.example.eatroom.viewmodels.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination()
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val selectedValue = remember { mutableStateOf("Customer") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("Customer", "Administrator", "Deliverer")
    val success = viewModel.success
    if (success)
        navigator.popBackStack()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Choose user type:")
        Spacer(modifier = Modifier.height(10.dp))
        Row() {
            items.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = Role.RadioButton
                    )
                ) {
                    RadioButton(
                        selected = isSelectedItem(item),
                        onClick = null
                    )
                    Text( text = item )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier.size(width = 280.dp, height = 50.dp),
            onClick = {
                viewModel.register(
                    RegisterRequest(
                        firstName,
                        lastName,
                        username,
                        password,
                        email,
                        phoneNumber
                    ),
                    selectedValue.value
                )
                navigator.popBackStack()
            }
        ) {
            Text(text = "Register")
        }
    }
}