package ru.rsreu.triprest.trip

import kotlinx.serialization.Serializable

@Serializable
data class Trip(val id: Int, val carId: Int, val distance: Int)