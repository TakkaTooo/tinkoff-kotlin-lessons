package ru.rsreu.astashkin10.car

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class CarService(
    private val db: Database,
    private val dao: CarDao
) {
    fun findAll(): List<Car> = dao.findAll()

    fun findById(id: Int): Car = dao.findById(id)

    fun create(manufacturer: String, year: Int) = transaction(db) {
        dao.create(manufacturer, year)
    }

    fun update(id: Int, manufacturer: String, year: Int) = transaction(db) {
        dao.update(id, manufacturer, year)
    }

    fun delete(id: Int): Int = transaction(db) {
        dao.delete(id)
    }
}