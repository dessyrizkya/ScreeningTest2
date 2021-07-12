package com.dessy.screeningtest.api

import com.dessy.screeningtest.model.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v2/596dec7f0f000023032b8017")
    fun getGuests(): Call<List<ResponseItem>>

}