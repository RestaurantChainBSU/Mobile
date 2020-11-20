package com.example.mobile.network.dishes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DishesItem (
    val dish_descr: String,
    val dish_name: String,
    val id: Int,
    val image_link: String,
    val price: Double
): Parcelable