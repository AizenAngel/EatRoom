package com.example.eatroom.viewmodels

import androidx.lifecycle.ViewModel
import com.example.eatroom.model.remote.RestaurantApi
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val api: RestaurantApi
) : ViewModel() {

}