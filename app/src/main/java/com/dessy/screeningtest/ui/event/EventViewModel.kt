package com.dessy.screeningtest.ui.event

import androidx.lifecycle.ViewModel
import com.dessy.screeningtest.model.EventEntity
import com.dessy.screeningtest.util.DataDummy

class EventViewModel: ViewModel() {

    private val dataDummy = DataDummy()

    fun getEvents() : List<EventEntity> = dataDummy.getEventDummy()
}