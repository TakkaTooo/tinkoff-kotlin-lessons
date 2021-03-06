package Entities

/**
 * The entity describing a car with a list of trips perfect on it.
 * Is a hybrid of [Car] and [CarTrips]
 * @property id id of the car on which the trips were made
 * @property manufacturer manufacturer manufacturer of this car
 * @property year year of manufacture of this machine
 * @property trips list of [Trip]s committed to cars with id = carId
 * @property isAvailable - true - Info available for output
 *                       - false - Info not available for output
 *                       (just for example)
 */
data class CarWithTrips(
    val id: Int,
    val manufacturer: String,
    val year: Int,
    val trips: List<Trip>?,
    val isAvailable: Boolean?
)