package ru.rsreu.astashkin09.model

import io.swagger.annotations.ApiModel

@ApiModel(value = "Car entity")
data class Car(val id: Int, val manufacturer: String, val year: Int)