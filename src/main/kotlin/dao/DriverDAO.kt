package dao

import model.Driver

class DriverDAO {
    companion object {
        /**
         * Container with cars (obtained from the database for example)
         */
        private val container = listOf(
            Driver(0, "Pavel", "123456"),
            Driver(1, "Alex", "212323"),
            Driver(2, "Oleg", "1337000000"),
            Driver(3, "Gabe", "333333"),
            Driver(4, "Pavel", "000000001")
        )

        /**
         * Returns all drivers
         */
        fun getAllDrivers(): List<Driver> = container

        /**
         * Returns the [Driver] by id
         * @param id id of a returning [Driver]
         */
        fun getDriverById(id: Int): Driver? = container.find { it.id == id }
    }
}