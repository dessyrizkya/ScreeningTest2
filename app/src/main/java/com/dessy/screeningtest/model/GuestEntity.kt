package com.dessy.screeningtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuestEntity(
    val birthdate: String,
    val name: String,
    val id: Int
) : Parcelable