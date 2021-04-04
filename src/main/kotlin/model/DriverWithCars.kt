package model

/**
 * Entity for storing information about drivers and all vehicles they traveled on.
 * Used to demonstrate the implementation of many-to-many relationships
 * through foreign keys in a database.
 * @property id identifier of [Driver].
 * @property name string name of [Driver].
 * @property drivingLicence number of driving licence in string representation.
 * @property carsIds list of all carIds of [Car]'s which made trip by this [Driver].
 */
data class DriverWithCars(val id: Int, val name: String, val drivingLicence: String, val carsIds: List<Int>)