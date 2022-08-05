package com.example.eatroom.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatroom.model.data.LoginRequest
import com.example.eatroom.model.data.RegisterRequest
import com.example.eatroom.model.data.UserType
import com.example.eatroom.model.remote.IdentityApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: IdentityApi
) : ViewModel() {

    var userType: UserType? by mutableStateOf(null)
    var success by mutableStateOf(false)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = try {
                api.login(LoginRequest(username, password)).role
            } catch(e: Exception) {
                e.printStackTrace()
                null
            }
            userType = response
        }
    }

    fun register(requestData: RegisterRequest, userType: String) {
        when(userType){
            "Customer" -> registerCustomer(requestData)
            "Administrator" -> registerAdmin(requestData)
            "Deliverer" -> registerDeliverer(requestData)
        }
    }

    private fun registerCustomer (requestData: RegisterRequest) {
        viewModelScope.launch {
            success = try {
                api.registerCustomer(requestData)
                true
            } catch(e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    private fun registerAdmin(requestData: RegisterRequest) {
        viewModelScope.launch {
            success = try {
                api.registerAdmin(requestData)
                true
            } catch(e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    private fun registerDeliverer(requestData: RegisterRequest) {
        viewModelScope.launch {
            success = try {
                api.registerDeliverer(requestData)
                true
            } catch(e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}