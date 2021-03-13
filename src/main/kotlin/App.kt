import org.sqlite.SQLiteException
import java.lang.StringBuilder
import java.sql.*

fun main() {
    // Init
    val client = Client("src/main/resources/test.db")
    val sb = StringBuilder()

    try {
        Initializer.createTables(client)

    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        Initializer.fillTables(client)
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    // Demo
    try {
        sb.append("Driver by id ${DataGetter.getDriverById(client, 3)}").append("\n")
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        sb.append("Driver with all cars: ${DataGetter.getDriverWithAllCars(client, 4)}").append("\n")
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        sb.append("Drivers by desc: ").append("\n")
        DataGetter.getDriversSortedByName(client, SortOrder.DESCENDING).forEach {
            sb.append(it).append("\n")
        }
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        sb.append("Cars with trips count > 1: ${DataGetter.getCarsWithTripsCountMoreThanOne(client)}").append("\n")
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        sb.append("Trips with distance > 15: ${DataGetter.getTripsByDistanceMoreThan(client, 15)}").append("\n")
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    try {
        sb.append("Drivers with trips and car info: ").append("\n")
        DataGetter.getDriversWithTripsAndCarInfo(client).forEach {
            sb.append(it).append("\n")
        }
    } catch (e: SQLException) {
        sb.append(e.message).append("\n")
    }

    // Removing

    try {
        Initializer.deleteAllTables(client)
    } catch (e: SQLiteException) {
        sb.append(e.message).append("\n")
    }

    println(sb)
}