package ru.rsreu.astashkin09.model

import io.swagger.annotations.ApiModel

@ApiModel(value = "Entity is the result of join Trip and Car by carId")
data class TripWithCar(val id: Int, val carId: Int, val manufacturer: String, val year: Int)