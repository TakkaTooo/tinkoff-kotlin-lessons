package ru.rsreu.triprest.trip

import kotlinx.serialization.Serializable

@Serializable
data class Car(val id: Int, val manufacturer: String, val year: Int)
