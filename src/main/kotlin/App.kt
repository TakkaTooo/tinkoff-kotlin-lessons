import jdbc.Client
import service.*
import java.lang.StringBuilder
import java.sql.SQLException

fun main() {

    val client = Client("src/main/resources/test.db")
    val sb = StringBuilder()

    with(sb) {

        // Demo
        try {
            // Init
            try {
                CarsharingInitializer.createAllTables(client)
                CarsharingInitializer.fillAllTables(client)

            } catch (e: CarsharingServiceOperationFaultException) {
            }
            // Demo
            append("Driver by id: ${DataGetter.getDriverById(client, 3)}").append("\n")

            append("Driver with all cars: ${DataGetter.getDriverWithAllCars(client, 6)}").append("\n")

            append("Drivers by desc: ").append("\n")
            DataGetter.getDriversSortedByName(client, SortOrder.DESCENDING).forEach {
                append(it).append("\n")
            }

            append("Cars with trips count > 1: ${DataGetter.getCarsWithTripsCountMoreThanOne(client)}").append("\n")

            append("Trips with distance > 30: ${DataGetter.getTripsByDistanceMoreThan(client, 30)}").append("\n")
            append("Trips with distance > 15: ${DataGetter.getTripsByDistanceMoreThan(client, 15)}").append("\n")

            append("Drivers with trips and car info: ").append("\n")
            DataGetter.getDriversWithTripsAndCarInfo(client).forEach {
                append(it).append("\n")
            }
        } catch (e: SQLException) {

        } finally {
            // Removing
            try {
                CarsharingDropper.dropAllTables(client)
            } catch (e: CarsharingServiceOperationFaultException) {
            } finally {
                client.close()
            }

        }
    }

    println(sb)
}
