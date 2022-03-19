package com.example.eatroom.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val name: String,
    val price: Int
) : Parcelable
