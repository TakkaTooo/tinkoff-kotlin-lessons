package ru.rsreu.carrest.car

import org.jetbrains.exposed.dao.id.IntIdTable


object Cars : IntIdTable() {
    var manufacturer = char("manufacturer", 20)
    var year = integer("year")
}