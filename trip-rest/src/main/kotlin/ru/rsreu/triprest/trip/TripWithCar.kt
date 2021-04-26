package ru.rsreu.triprest.trip

import kotlinx.serialization.Serializable

@Serializable
data class TripWithCar(val id: Int, val carId: Int, val manufacturer: String, val year: Int, val distance: Int)
