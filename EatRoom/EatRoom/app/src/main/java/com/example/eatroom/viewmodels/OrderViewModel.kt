package com.example.eatroom.viewmodels

import androidx.lifecycle.ViewModel
import com.example.eatroom.model.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

}