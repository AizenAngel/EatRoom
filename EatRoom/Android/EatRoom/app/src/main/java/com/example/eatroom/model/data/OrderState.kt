package com.example.eatroom.model.data

import com.google.gson.annotations.SerializedName

enum class OrderState {
    @SerializedName("0")
    PREPARING,
    @SerializedName("1")
    DELIVERING,
    @SerializedName("2")
    DELIVERED
}