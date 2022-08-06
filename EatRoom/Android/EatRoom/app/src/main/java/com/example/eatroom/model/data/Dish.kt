package com.example.eatroom.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val id: Int,
    val name: String,
    val imageFile: String,
    val price: Int,
    val restaurantId: Int
) : Parcelable
