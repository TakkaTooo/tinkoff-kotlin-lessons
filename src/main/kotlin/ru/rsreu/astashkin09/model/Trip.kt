package ru.rsreu.astashkin09.model

import io.swagger.annotations.ApiModel

@ApiModel(value = "Trip entity")
data class Trip(val id: Int, val carId: Int, val distance: Int)