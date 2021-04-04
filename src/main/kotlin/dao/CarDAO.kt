package dao

import model.Car

class CarDAO {
    companion object {
        /**
         * Container with cars (obtained from the database for example)
         */
        private val container =
            listOf(
                Car(0, 1, "VAZ", 2019),
                Car(1, 1, "Renault", 2019),
                Car(2, 0, "BMW", 2018),
                Car(3, 2, "Hyundai", 2020),
                Car(4, 3, "Renault", 2020),
                Car(5, 3, "Kia", 2018)
            )

        /**
         * Returns all cars
         */
        fun getAllCars(): List<Car> = container

        /**
         * Returns the [Car] by id
         * @param id id of a returning [Car]
         */
        fun getCarById(id: Int): Car? = container.find { it.id == id }
    }
}