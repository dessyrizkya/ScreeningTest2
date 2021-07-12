package com.dessy.screeningtest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity (
    var nama: String,
    var event: String = "",
    var guest: String = ""
) : Parcelable