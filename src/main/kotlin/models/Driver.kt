package models

/**
 * Class describing the entity of the car sharing driver.
 * @property id identifier of driver.
 * @property name string name of driver.
 * @property drivingLicence number of driving licence in string representation.
 */
data class Driver(val id: Int, val name: String, val drivingLicence: String)