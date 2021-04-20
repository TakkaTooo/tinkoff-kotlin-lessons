package ru.rsreu.astashkin10.trip

import io.ktor.features.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rsreu.astashkin10.car.CarDao
import ru.rsreu.astashkin10.car.CarService

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

    fun create(carId: Int, distance: Int): Trip = transaction(db) {
        val id = Trips.insertAndGetId {
            it[Trips.carId] = carId
            it[Trips.distance] = distance
        }
        Trip(id.value, carId, distance)
    }

    fun update(id: Int, carId: Int, distance: Int): Int = transaction(db) {
        Trips.update({ Trips.id eq id }) {
            it[Trips.carId] = carId
            it[Trips.distance] = distance
        }
    }

    fun delete(id: Int): Int = transaction(db) {
        Trips.deleteWhere { Trips.id eq id }
    }

    fun changeCar(id: Int, newCarId: Int): Int = transaction(db) {
        Trips.update({ Trips.id eq id }) { trip ->
            trip[Trips.carId] = newCarId
        }
    }

    private fun extractTrip(row: ResultRow) = Trip(
        row[Trips.id].value,
        row[Trips.carId],
        row[Trips.distance]
    )
}