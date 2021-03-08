package DBO

import Entities.CarTrips
import Entities.Trip
import java.time.LocalDateTime

/**
 * Emulator of DBO for Entities.CarTrips
 */
class CarTripsDBO {
    companion object {
        /**
         * Container with cars trips (obtained from the database for example)
         */
        private val container: List<CarTrips> =
            listOf(
                CarTrips(
                    0, listOf(
                        Trip(0, 10, LocalDateTime.parse("2021-01-01T14:00")),
                        Trip(1, 15, LocalDateTime.parse("2021-01-02T12:00")),
                        Trip(2, 5, LocalDateTime.parse("2021-01-02T12:00"))
                    ), true
                ),
                CarTrips(
                    1, listOf(
                        Trip(3, 2, LocalDateTime.parse("2021-02-20T12:00")),
                        Trip(4, 10, LocalDateTime.parse("2021-02-20T23:00"))
                    ), true
                ),
                CarTrips(
                    2, listOf(
                        Trip(5, 6, LocalDateTime.parse("2021-02-28T12:00")),
                        Trip(6, 1, LocalDateTime.parse("2021-03-01T10:00"))
                    ), false
                ),
                CarTrips(
                    3, listOf(
                        Trip(7, 11, LocalDateTime.parse("2021-03-01T17:00")),
                        Trip(8, 18, LocalDateTime.parse("2021-03-01T21:00"))
                    ), true
                ),
                CarTrips(
                    4, listOf(
                        Trip(9, 10, LocalDateTime.parse("2021-03-04T10:30"))
                    ), true
                ),
                CarTrips(
                    5, listOf(
                        Trip(10, 14, LocalDateTime.parse("2021-03-05T15:30")),
                        Trip(11, 16, LocalDateTime.parse("2021-03-05T17:45")),
                        Trip(12, 23, LocalDateTime.parse("2021-03-05T20:43"))
                    ), false
                ),
                CarTrips(
                    0, listOf (
                        Trip(13, 777, LocalDateTime.parse("2021-03-04T11:20"))
                            )
                    , false)

            )

        /**
         * Return all Entities.CarTrips
         */
        fun getAllCarTrips(): List<CarTrips> = container

        /**
         * Returns List<[Entities.CarTrips]> by carId (all lists of trips), returns emptyList() if not exists [Entities.CarTrips] for this carId
         * @param id id of the car on which the trips were made
         */
        fun getTripsByCarId(id: Int): List<CarTrips> = container.filter { it.carId == id }
    }
}