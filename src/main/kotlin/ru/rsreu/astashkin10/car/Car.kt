package ru.rsreu.astashkin10.car

import kotlinx.serialization.Serializable

@Serializable
data class Car(val id: Int, val manufacturer: String, val year: Int)
