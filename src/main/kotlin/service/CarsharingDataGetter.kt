package service

import jdbc.Client
import model.*
import java.sql.SQLException

enum class SortOrder(val sql: String) {
    ASCENDING("ACS"),
    DESCENDING("DESC");
}

class DataGetter {
    companion object {

        /**
         * Returns the [Driver] instance by id from the database (null if query returned zero rows).
         * @param client class-client for working with database.
         * @param id identifier of [Driver] for get from database.
         */
        fun safeGetDriverById(client: Client, id: Int): Driver? {
            val sql = """
            SELECT *
             FROM Driver
             WHERE id = ? """.trimIndent()
            return try {
                val result = client.executeQuery(sql, id.toString())
                Driver(
                    result.first()["id"] as Int,
                    result.first()["name"] as String,
                    result.first()["drivingLicence"] as String)
            } catch (e: SQLException) {
                null
            }
        }

        /**
         * Returns the [Driver] instance by id from the database.
         * @param client class-client for working with database.
         * @param id identifier of [Driver] for get from database.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getDriverById(client: Client, id: Int): Driver = safeGetDriverById(client, id)
            ?: throw SQLException("CarsharingDataGetter.getDriverById throw SQLException: Query returned zero rows")


        /**
         * Returns List with [Trip]s instances with distance more than distance-arg
         * (empty list if query returned zero rows).
         * @param client class-client for working with database.
         * @param distance distance for compare.
         */
        fun safeGetTripsByDistanceMoreThan(client: Client, distance: Int): List<Trip> {
            val sql = """
                SELECT *
                 FROM Trip
                 WHERE distance > ?
            """.trimIndent()
            return try {
                val result = client.executeQuery(sql, distance.toString())
                result.map {
                    Trip(it["id"] as Int, it["carId"] as Int, it["driverId"] as Int, it["distance"] as Int)
                }
            } catch (e: SQLException) {
                emptyList()
            }
        }

        /**
         * Returns List with [Trip]s instances with distance more than distance-arg.
         * @param client class-client for working with database.
         * @param distance distance for compare.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getTripsByDistanceMoreThan(client: Client, distance: Int): List<Trip> {
            val result = safeGetTripsByDistanceMoreThan(client, distance)
            if (result.isNotEmpty()) {
                return result
            }
            throw SQLException("CarsharingDataGetter.getTripsByDistanceMoreThan throw SQLException: Query returned zero rows")
        }

        /**
         * Returns instances that are the result of the join of three tables
         * (empty list if query returned zero rows).
         * @param client class-client for working with database.
         */
        fun safeGetDriversWithTripsAndCarInfo(client: Client): List<DriverWithTripAndCar> {
            val sql = """
                SELECT Driver.id as id, name, Trip.id as tripId, manufacturer, distance 
                 FROM (Driver JOIN Trip on Driver.id = driverId) JOIN Car on Car.id = carId
            """.trimIndent()
            return try {
                val result = client.executeQuery(sql)
                result.map {
                    DriverWithTripAndCar(
                        it["id"] as Int,
                        it["name"] as String,
                        it["manufacturer"] as String,
                        it["tripId"] as Int,
                        it["distance"] as Int)
                }
            } catch (e: SQLException) {
                emptyList()
            }
        }

        /**
         * Returns instances that are the result of the join of three tables.
         * @param client class-client for working with database.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getDriversWithTripsAndCarInfo(client: Client): List<DriverWithTripAndCar> {
            val result = safeGetDriversWithTripsAndCarInfo(client)
            if (result.isNotEmpty()) {
                return result
            }
            throw SQLException("CarsharingDataGetter.getDriversWithTripsAndCarInfo throw SQLException: Query returned zero rows")
        }

        /**
         * Returns cars with more than 1 trips.
         * (empty list if query returned zero rows).
         * @param client class-client for working with database.
         */
        fun safeGetCarsWithTripsCountMoreThanOne(client: Client): List<Car> {
            val sql = """
                SELECT Car.id, manufacturer, year
                 FROM Car JOIN Trip on Car.id = carId
                 GROUP BY carId   
                 HAVING COUNT(carId) > 1
            """.trimIndent()
            return try {
                val result = client.executeQuery(sql)
                result.map {
                    Car(it["id"] as Int, it["manufacturer"] as String, it["year"] as Int)
                }
            } catch (e: SQLException) {
                emptyList()
            }

        }

        /**
         * Returns cars with more than 1 trips.
         * @param client class-client for working with database.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getCarsWithTripsCountMoreThanOne(client: Client): List<Car> {
            val result = safeGetCarsWithTripsCountMoreThanOne(client)
            if (result.isNotEmpty()) {
                return result
            }
            throw SQLException("CarsharingDataGetter.getCarsWithTripsCountMoreThanOne throw SQLException: Query returned zero rows")
        }

        /**
         * !!! FOR DEMONSTRATION many-to-many !!!
         * Returns the [DriverWithAllCars] entity
         * (information about the driver
         *  and a list of id of the cars on which he traveled)
         *  (null if query returned zero rows).
         *  @param client class-client for working with database.
         *  @param id identifier of [Driver] for get from database.
         */
        fun safeGetDriverWithAllCars(client: Client, id: Int): DriverWithAllCars? {
            val sql = """ 
                SELECT id, name, drivingLicence, carId
                 FROM Driver LEFT JOIN DriversCars on id = driverId 
                 WHERE driverId = ?
            """.trimIndent()
            return try {
                val result = client.executeQuery(sql, id.toString())
                DriverWithAllCars(
                    result.first()["id"] as Int,
                    result.first()["name"] as String,
                    result.first()["drivingLicence"] as String,
                    result.map {
                        it["carId"] as Int
                    }
                )
            } catch (e: SQLException) {
                null
            }
        }

        /**
         * !!! FOR DEMONSTRATION many-to-many !!!
         * Returns the [DriverWithAllCars] entity
         * (information about the driver
         *  and a list of id of the cars on which he traveled).
         *  @param client class-client for working with database.
         *  @param id identifier of [Driver] for get from database.
         *  @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getDriverWithAllCars(client: Client, id: Int): DriverWithAllCars = safeGetDriverWithAllCars(client, id)
            ?: throw SQLException("CarsharingDataGetter.getDriverWithAllCars throw SQLException: Query returned zero rows")

        /**
         * Returns a list of all [Driver]s sorted by name by ascending.
         * @param client class-client for working with database.
         * (empty list if query returned zero rows).
         */
        fun safeGetDriversSortedByName(client: Client): List<Driver> =
            safeGetDriversSortedByName(client, SortOrder.ASCENDING)

        /**
         * Returns a list of all [Driver]s sorted by name by [sortOrder]
         * (empty list if query returned zero rows).
         * @param client class-client for working with database.
         * @param sortOrder order of sorting.
         */
        fun safeGetDriversSortedByName(client: Client, sortOrder: SortOrder): List<Driver> {
            val sql = """
                SElECT *
                 FROM Driver
                 ORDER BY name
            """.trimIndent() + " " + sortOrder.sql
            return try {
                val result = client.executeQuery(sql)
                result.map {
                    Driver(
                        it["id"] as Int,
                        it["name"] as String,
                        it["drivingLicence"] as String
                    )
                }
            } catch (e: SQLException) {
                emptyList()
            }
        }

        /**
         * Returns a list of all [Driver]s sorted by name by [sortOrder]
         * @param client class-client for working with database.
         * @param sortOrder order of sorting.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getDriversSortedByName(client: Client, sortOrder: SortOrder): List<Driver> {
            val result = safeGetDriversSortedByName(client, sortOrder)
            if (result.isNotEmpty()) {
                return result
            }
            throw SQLException("CarsharingDataGetter.getDriversSortedByName throw SQLException: Query returned zero rows")
        }

        /**
         * Returns a list of all [Driver]s sorted by name by ascending.
         * @param client class-client for working with database.
         * @throws SQLException in case if query returned zero rows.
         */
        @Throws(SQLException::class)
        fun getDriversSortedByName(client: Client): List<Driver> = getDriversSortedByName(client, SortOrder.ASCENDING)
    }
}

