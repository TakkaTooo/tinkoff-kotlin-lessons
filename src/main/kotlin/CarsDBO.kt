/**
 * Emulator of DBO for Cars
 */
class CarsDBO {
    companion object {
        /**
         * Container with cars (obtained from the database for example)
         */
        private val container: List<Car> =
            listOf(
                Car(0, "VAZ", 2019),
                Car(1, "Renault", 2019),
                Car(2, "BMW", 2018),
                Car(3, "Hyundai", 2020),
                Car(4, "Renault", 2020),
                Car(5, "Kia", 2018)
            )

        /**
         * Returns all cars
         */
        fun getAllCars(): List<Car> = container

        /**
         * Returns the car by id
         * @param id id of a returning car
         */
        fun getCarById(id: Int): Car? = container.find { it.id == id }

        /**
         * Returns cars list by manufacturer
         * @param manufacturer manufacturer of cars in returning list
         */
        fun getCarByManufacturer(manufacturer: String): List<Car> = container.filter { it.manufacturer == manufacturer }
    }
}