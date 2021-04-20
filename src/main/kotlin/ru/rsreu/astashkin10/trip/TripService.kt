package ru.rsreu.astashkin10.trip

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rsreu.astashkin10.car.CarService

class TripService(
    private val dao: TripDao,
    private val db: Database
) {
    fun findAll(): List<Trip> = dao.findAll()

    fun findById(id: Int): Trip = dao.findById(id)

    fun findByCarId(carId: Int): List<Trip> = dao.findByCarId(carId)

    fun create(carId: Int, distance: Int): Trip = transaction(db) {
        dao.create(carId, distance)
    }

    fun update(id: Int, carId: Int, distance: Int): Int = transaction(db) {
        dao.update(id, carId, distance)
    }

    fun delete(id: Int): Int = transaction(db) {
        dao.delete(id)
    }

    fun changeCar(id: Int, newCarId: Int): Int = transaction {
        dao.changeCar(id, newCarId)
    }
}