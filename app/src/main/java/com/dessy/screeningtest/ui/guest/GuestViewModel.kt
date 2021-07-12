package com.dessy.screeningtest.ui.guest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dessy.screeningtest.api.RemoteSource
import com.dessy.screeningtest.model.ResponseItem

class GuestViewModel: ViewModel() {

    private val remote = RemoteSource()

    fun getGuests(): LiveData<List<ResponseItem>> = remote.getGuests()
}