package ru.rsreu.carrest.car

import kotlinx.serialization.Serializable

@Serializable
data class Car(val id: Int, val manufacturer: String, val year: Int)
