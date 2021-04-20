package ru.rsreu.astashkin10.trip

import org.jetbrains.exposed.dao.id.IntIdTable

object Trips : IntIdTable() {
    val carId = integer("car_id")
    val distance = integer("distance")
}