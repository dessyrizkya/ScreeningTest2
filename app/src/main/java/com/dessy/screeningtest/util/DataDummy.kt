package com.dessy.screeningtest.util

import com.dessy.screeningtest.model.EventEntity
import com.dessy.screeningtest.model.GuestEntity

class DataDummy {

    private val events = ArrayList<EventEntity>()

    fun getEventDummy() : List<EventEntity> {
        events.add(
            EventEntity(
                "Personal Branding : Cara Meningkatkan Citra Diri Untuk Menunjang Potensi Karir " +
                        "di Era Modern",
                "10 Juli 2021",
                "https://s3-ap-southeast-1.amazonaws.com/loket-production-sg/images/banner/" +
                        "20210624210137_60d49041994d7.jpg",
                106.844089,
                -6.18535)
        )

        events.add(
            EventEntity(
                "How to Make your Passion Great by Increasing your Competence - Dikelas #2",
                "10 Juli 2021",
                "https://s3-ap-southeast-1.amazonaws.com/loket-production-sg/images/banner/20210626084918_60d6879e61485.jpg",
                107.436310,
                -6.523839
            )
        )

        events.add(
            EventEntity(
                "Trending Topic Conference",
                "18 Juli 2021",
                "https://cdn.kiostix.com/media/17673/conversions/medium.jpg",
                107.609810,
                -6.914744
            )
        )

        events.add(
            EventEntity(
                "Uitalks 2.0",
                "11 Juli 2021",
                "https://cdn.kiostix.com/media/17678/conversions/medium.jpg",
                106.834,
                -6.3679
            )
        )

        return events
    }
}