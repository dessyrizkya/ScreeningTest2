package com.dessy.screeningtest.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dessy.screeningtest.model.ResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteSource {

    fun getGuests(): LiveData<List<ResponseItem>> {
        val guests = MutableLiveData<List<ResponseItem>>()

        CoroutineScope(IO).launch {
            try {
                val response = RetrofitClient.instance.getGuests().await()
                guests.postValue(response)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return guests
    }
}
