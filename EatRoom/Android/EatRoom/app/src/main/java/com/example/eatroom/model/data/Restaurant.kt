package com.example.eatroom.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val name: String,
    val id: Int,
    val logoFile: String?
) : Parcelable
