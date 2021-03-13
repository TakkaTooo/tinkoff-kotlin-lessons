package models

import java.time.LocalDateTime

/**
 * Entity describing the trip.
 * @property id id of this trip.
 * @property carId id of the car on which the trip was made.
 * @property driverId id of the driver who made the trip.
 * @property distance kilometers traveled during this trip.
 */
data class Trip(val id: Int, val carId: Int, val driverId: Int, val distance: Int)