package models

/**
 * Entity for JOIN tables Driver Car Trip.
 * @property id identifier of [Driver].
 * @property name string name of [Driver].
 * @property @property manufacturer manufacturer of [Car].
 * @property id identifier of [Trip].
 * @property distance kilometers traveled during this [Trip].
 *
 */
data class DriverWithTripAndCar(
    val id: Int,
    val name: String,
    val manufacturer: String,
    val tripId: Int,
    val distance: Int,
)