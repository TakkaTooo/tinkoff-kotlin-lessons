package model

/**
 * A class describing the entity of a car used, for example, in car sharing.
 * @property id id of this car.
 * @property manufacturer manufacturer of car.
 * @property year year of manufacture of car.
 */
data class Car(val id: Int, val manufacturer: String, val year: Int)