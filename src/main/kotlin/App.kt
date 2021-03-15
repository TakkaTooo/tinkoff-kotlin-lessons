import jdbc.Client
import service.*
import java.lang.StringBuilder
import java.sql.SQLException

fun main() {
    // Init
    val client = Client("src/main/resources/test.db")
    val sb = StringBuilder()

    with(sb) {
        try {
            CarsharingInitializer.createTables(client)
            CarsharingInitializer.fillTables(client)

        } catch (e: CarsharingServiceOperationFaultException) {
            append(e.message).append("\n")
        }

        // Demo
        append("Driver by id: ${DataGetter.safeGetDriverById(client, 3)}").append("\n")

        append("Driver with all cars: ${DataGetter.safeGetDriverWithAllCars(client, 6)}").append("\n")

        append("Drivers by desc: ").append("\n")
        DataGetter.safeGetDriversSortedByName(client, SortOrder.DESCENDING).forEach {
            append(it).append("\n")
        }

        append("Cars with trips count > 1: ${DataGetter.safeGetCarsWithTripsCountMoreThanOne(client)}").append("\n")

        append("Trips with distance > 30: ${DataGetter.safeGetTripsByDistanceMoreThan(client, 30)}").append("\n")
        append("Trips with distance > 15: ${DataGetter.safeGetTripsByDistanceMoreThan(client, 15)}").append("\n")

        append("Drivers with trips and car info: ").append("\n")
        DataGetter.safeGetDriversWithTripsAndCarInfo(client).forEach {
            append(it).append("\n")
        }

        //Removing
        try {
            CarsharingDropper.deleteAllTables(client)
        } catch (e: CarsharingServiceOperationFaultException) {
            append(e.message).append("\n")
        }
    }

    client.close()
    println(sb)
}
