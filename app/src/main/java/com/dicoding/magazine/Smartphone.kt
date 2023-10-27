package com.dicoding.magazine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Smartphone(
    val photo: Int,
    val name: String,
    val price: Double,
    val description: String
) : Parcelable
