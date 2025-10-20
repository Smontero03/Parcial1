package com.example.parcial1.data.model

data class PitStop(
    val id: Int = 0,
    val raceName: String,
    val driverName: String,
    val teamName: String,
    val lap: Int,
    val stopTime: Float
)
