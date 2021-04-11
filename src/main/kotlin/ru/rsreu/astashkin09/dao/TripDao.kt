package ru.rsreu.astashkin09.dao

import ru.rsreu.astashkin09.model.Trip

class TripDao {
    companion object {
        private val container = listOf(
            Trip(0, 0, 10),
            Trip(1, 0, 15),
            Trip(2, 1, 11),
            Trip(3, 1, 12)
        )

        fun getTrips(): List<Trip> = container

        fun getTripById(id: Int): Trip? = container.find { it.id == id }
    }
}