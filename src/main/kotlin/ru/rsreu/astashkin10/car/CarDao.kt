package ru.rsreu.astashkin10.car

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CarDao(private val db: Database) {
    fun findAll(): List<Car> = transaction(db) {
        Cars.selectAll().map(::extractCar)
    }

    fun findById(id: Int): Car = transaction(db) {
        Cars.select { Cars.id eq id }.map(::extractCar).first()
    }

    fun create(manufacturer: String, year: Int): Car = transaction(db) {
        val id = Cars.insertAndGetId {
            it[Cars.manufacturer] = manufacturer
            it[Cars.year] = year
        }
        Car(id.value, manufacturer, year)
    }

    fun update(id: Int, manufacturer: String, year: Int): Int = transaction(db) {
        Cars.update({ Cars.id eq id }) {
            it[Cars.manufacturer] = manufacturer
            it[Cars.year] = year
        }
    }

    fun delete(id: Int): Int = transaction(db) {
        Cars.deleteWhere { Cars.id eq id }
    }

    private fun extractCar(row: ResultRow): Car = Car(
        row[Cars.id].value,
        row[Cars.manufacturer],
        row[Cars.year]
    )
}