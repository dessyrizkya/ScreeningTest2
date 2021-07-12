package com.dessy.screeningtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val response: List<ResponseItem?>? = null
) : Parcelable

@Parcelize
data class ResponseItem(
	val birthdate: String? = null,
	val name: String? = null,
	val id: Int? = null
) : Parcelable
