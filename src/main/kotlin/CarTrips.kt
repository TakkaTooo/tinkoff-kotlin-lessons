/**
 * A class describing the entity of trips made by [Car] entity
 * @property carId id of the car on which the trips were made
 * @property trips list of [Trip]s committed to cars with id = carId
 * @property isAvailable - true - Info available for output
 *                       - false - Info not available for output
 *                       (just for example)
 */
data class CarTrips(val carId: Int, val trips: List<Trip>, val isAvailable: Boolean)