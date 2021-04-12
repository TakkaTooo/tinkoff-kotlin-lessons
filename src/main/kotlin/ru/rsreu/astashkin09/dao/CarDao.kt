package ru.rsreu.astashkin09.dao

import ru.rsreu.astashkin09.model.Car

class CarDao {
    companion object {
        private val container = listOf(
            Car(0, "Renault", 2020),
            Car(1, "Hyundai", 2015)
        )

        fun getCars(): List<Car> = container

        fun getCarById(id: Int): Car? = container.find { it.id == id }
    }
}