package ru.rsreu.astashkin10.trip

import kotlinx.serialization.Serializable

@Serializable
data class Trip(val id: Int, val carId: Int, val distance: Int)