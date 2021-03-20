import jdbc.Client
import service.*
import java.lang.StringBuilder
import java.sql.SQLException

fun main() {

    val client = Client("src/main/resources/test.db")
    val sb = StringBuilder()

    with(sb) {

        runCatching {
            // Init
            CarsharingInitializer.createAllTables(client)
            CarsharingInitializer.fillAllTables(client)

            // Demo
            append("Driver by id: ${CarsharingService.getDriverById(client, 3)}").append("\n")

            append("Driver with all cars: ${CarsharingService.getDriverWithAllCars(client, 6)}").append("\n")

            append("Drivers by desc: ").append("\n")
            CarsharingService.getDriversSortedByName(client, SortOrder.DESCENDING).forEach {
                append(it).append("\n")
            }

            append("Cars with trips count > 1: ${CarsharingService.getCarsWithTripsCountMoreThanOne(client)}").append("\n")

            append("Trips with distance > 30: ${CarsharingService.getTripsByDistanceMoreThan(client, 30)}").append("\n")

            append("Trips with distance > 15: ${CarsharingService.getTripsByDistanceMoreThan(client, 15)}").append("\n")

            append("Drivers with trips and car info: ").append("\n")
            CarsharingService.getDriversWithTripsAndCarInfo(client).forEach {
                append(it).append("\n")
            }
        }.let {
            println(this)
            runCatching {
                println("Deleting tables...")
                CarsharingDropper.dropAllTables(client)
            }.let {
                println("Close connection...")
                client.close()
            }
        }
    }
}
