package com.example.eatroom.model.data

import com.google.gson.annotations.SerializedName

enum class UserType {
    @SerializedName("Customer")
    USER,
    @SerializedName("Administrator")
    OWNER,
    @SerializedName("Deliverer")
    DRIVER
}