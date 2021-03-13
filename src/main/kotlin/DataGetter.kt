import models.*
import java.lang.Exception

enum class SortOrder(val sql: String) {
    ASCENDING("ACS"),
    DESCENDING("DESC");
}

class DataGetter {
    companion object {

        /**
         * Returns the [Driver] instance by id from the database.
         * @param client class-client for working with database.
         * @param id identifier of [Driver] for get from database.
         */
        fun getDriverById(client: Client, id: Int): Driver {
            val sql = """
            SELECT *
             FROM Driver
             WHERE id = ? """.trimIndent()

            val result = client.executeQuery(sql, id.toString())
            return Driver(result[0]["id"] as Int, result[0]["name"] as String, result[0]["drivingLicence"] as String)
        }

        /**
         * Returns List with [Trip]s instances with distance more than distance-arg.
         * @param client class-client for working with database.
         * @param distance distance for compare.
         */
        fun getTripsByDistanceMoreThan(client: Client, distance: Int): List<Trip> {
            val sql = """
                SELECT *
                 FROM Trip
                 WHERE distance > ?
            """.trimIndent()

            val result = client.executeQuery(sql, distance.toString())
            return result.map {
                Trip(it["id"] as Int, it["carId"] as Int, it["driverId"] as Int, it["distance"] as Int)
            }
        }

        /**
         * Returns instances that are the result of the join of three tables.
         * @param client class-client for working with database.
         */
        fun getDriversWithTripsAndCarInfo(client: Client): List<DriverWithTripAndCar> {
            val sql = """
                SELECT Driver.id as id, name, Trip.id as tripId, manufacturer, distance 
                 FROM (Driver JOIN Trip on Driver.id = driverId) JOIN Car on Car.id = carId
            """.trimIndent()

            val result = client.executeQuery(sql)
            return result.map {
                DriverWithTripAndCar(
                    it["id"] as Int,
                    it["name"] as String,
                    it["manufacturer"] as String,
                    it["tripId"] as Int,
                    it["distance"] as Int)
            }
        }

        /**
         * Returns cars with more than 1 trips.
         * @param client class-client for working with database.
         */
        fun getCarsWithTripsCountMoreThanOne(client: Client): List<Car> {
            val sql = """
                SELECT Car.id, manufacturer, year
                 FROM Car JOIN Trip on Car.id = carId
                 GROUP BY carId   
                 HAVING COUNT(carId) > 1
            """.trimIndent()

            val result = client.executeQuery(sql)
            return result.map {
                Car(it["id"] as Int, it["manufacturer"] as String, it["year"] as Int)
            }
        }

        /**
         * !!! FOR DEMONSTRATION many-to-many !!!
         * Returns the [DriverWithAllCars] entity
         * (information about the driver.
         *  and a list of id of the cars on which he traveled).
         *  @param client class-client for working with database.
         *  @param id identifier of [Driver] for get from database.
         */
        fun getDriverWithAllCars(client: Client, id: Int): DriverWithAllCars {
            val sql = """ 
                SELECT id, name, drivingLicence, carId
                 FROM Driver LEFT JOIN DriversCars on id = driverId 
                 WHERE driverId = ?
            """.trimIndent()

            val result = client.executeQuery(sql, id.toString())
            return DriverWithAllCars(
                result[0]["id"] as Int,
                result[0]["name"] as String,
                result[0]["drivingLicence"] as String,
                result.map {
                    it["carId"] as Int
                }
            )
        }

        /**
         * Returns a list of all [Driver]s sorted by name by ascending.
         * @param client class-client for working with database.
         */
        fun getDriversSortedByName(client: Client): List<Driver> = getDriversSortedByName(client, SortOrder.ASCENDING)

        /**
         * Returns a list of all [Driver]s sorted by name by [sortOrder].
         * @param client class-client for working with database.
         * @param sortOrder order of sorting.
         */
        fun getDriversSortedByName(client: Client, sortOrder: SortOrder): List<Driver> {
            val sql = """
                SElECT *
                 FROM Driver
                 ORDER BY name
            """.trimIndent() + " " + sortOrder.sql

            val result = client.executeQuery(sql)
            return result.map {
                Driver(
                    it["id"] as Int,
                    it["name"] as String,
                    it["drivingLicence"] as String
                )
            }
        }
    }
}

