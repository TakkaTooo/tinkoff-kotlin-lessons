package ru.rsreu.triprest.trip

import io.ktor.features.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class TripDao(private val db: Database) {
    fun findAll(): List<Trip> = transaction(db) {
        Trips.selectAll().map(::extractTrip)
    }

    fun findById(id: Int): Trip = transaction(db) {
        Trips.select {
            Trips.id eq id
        }.map(::extractTrip).first()
    }

    fun findByCarId(carId: Int): List<Trip> = transaction(db) {
        Trips.select {
            Trips.carId eq carId
        }.map(::extractTrip)
    }

    private fun extractTrip(row: ResultRow) = Trip(
        row[Trips.id].value,
        row[Trips.carId],
        row[Trips.distance]
    )
}