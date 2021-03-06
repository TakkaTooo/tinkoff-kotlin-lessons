package Entities

import java.time.LocalDateTime

/**
 * Entity describing the trip
 * @property id id of this trip
 * @property distance kilometers traveled during this trip
 * @property date date and time of this trip
 */
data class Trip(val id: Int, val distance: Int, val date: LocalDateTime)