package com.example.eatroom.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val id: Int,
    val dishes: MutableList<Dish>
) : Parcelable
