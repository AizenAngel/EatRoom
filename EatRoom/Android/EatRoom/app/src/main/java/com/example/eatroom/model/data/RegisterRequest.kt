package com.example.eatroom.model.data

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val email: String,
    val phoneNumber: String
)
