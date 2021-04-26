package ru.rsreu.carrest.car

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CarDao(private val db: Database) {
    fun findAll(): List<Car> = transaction(db) {
        Cars.selectAll().map(::extractCar)
    }

    fun findById(id: Int): Car = transaction(db) {
        Cars.select { Cars.id eq id }.map(::extractCar).first()
    }

    private fun extractCar(row: ResultRow): Car = Car(
        row[Cars.id].value,
        row[Cars.manufacturer],
        row[Cars.year]
    )
}