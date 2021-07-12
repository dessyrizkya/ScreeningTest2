package com.dessy.screeningtest.ui.event

import com.dessy.screeningtest.model.EventEntity

interface EvenAdapterClickListener {
    fun onItemClickListener(eventEntity: EventEntity)
}