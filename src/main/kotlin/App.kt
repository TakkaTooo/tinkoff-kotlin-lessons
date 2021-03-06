import DBUtility.Companion.getSortedByManufacturer
import DBUtility.Companion.getGroupedByYear
import DBUtility.Companion.getQuantityOf

fun main() {
    // Demonstration
    println("All cars: ")
    CarsDBO.getAllCars().forEach { println(it) }
    println("Car by ID: ")
    println(CarsDBO.getCarById(0))
    println("Cars by manufacturer: ")
    CarsDBO.getCarByManufacturer("Renault").forEach { println(it) }

    println("All car trips: ")
    CarTripsDBO.getAllCarTrips().forEach { println(it) }
    println("Trips by car: ")
    println(CarTripsDBO.getTripsByCarId(0))

    println("Car join CarTrips: ")
    val cwt = DBUtility.getCarsWithTrips()
    cwt.forEach { println(it) }
    println("Car join CarTrips sorted descending by manufacturer: ")
    cwt.getSortedByManufacturer(SortOrder.DESCENDING).forEach { println(it) }
    println("Car join CarTrips grouped by year: ")
    cwt.getGroupedByYear().forEach { println(it) }
    println("The number of cars with more than 2 trips: ${cwt.getQuantityOf { it.trips?.size ?: 0 > 2 }}")
}