package com.example.eatroom.viewmodels

import androidx.lifecycle.ViewModel
import com.example.eatroom.model.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

}