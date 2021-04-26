package ru.rsreu.triprest.trip
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class TripService(
    private val dao: TripDao,
    private val db: Database
) {
    fun findAll(): List<Trip> = dao.findAll()

    fun findById(id: Int): Trip = dao.findById(id)

    fun findByCarId(carId: Int): List<Trip> = dao.findByCarId(carId)
}