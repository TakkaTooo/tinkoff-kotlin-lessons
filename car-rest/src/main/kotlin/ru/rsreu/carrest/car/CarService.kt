package ru.rsreu.carrest.car

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class CarService(
    private val db: Database,
    private val dao: CarDao
) {
    fun findAll(): List<Car> = dao.findAll()

    fun findById(id: Int): Car = dao.findById(id)
}