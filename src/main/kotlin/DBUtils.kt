import DBO.CarTripsDBO
import DBO.CarsDBO
import Entities.CarWithTrips

/**
 * Enum class describing 2 sorting orders
 */
enum class SortOrder {
    ASCENDING, DESCENDING
}

class DBUtility {
    companion object {
        /**
         * Collects data from the DBO.CarsDBO class and converts it into a list of Entities.CarWithTrips using the data obtained from the DBO.CarTripsDBO class
         */
        fun getCarsWithTrips(): List<CarWithTrips> = CarsDBO.getAllCars().map {
            val carTrips = CarTripsDBO.getTripsByCarId(it.id)
            val trips = carTrips.asSequence().map { carTrips -> carTrips.trips }.flatten().toList()
            CarWithTrips(it.id, it.manufacturer, it.year, trips)
        }
        /**
         * Returns a list sorted ascending by manufacturer name
         */
        fun List<CarWithTrips>.getSortedByManufacturer(): List<CarWithTrips> =
            this.sortedBy { it.manufacturer }

        /**
         * Returns a list sorted by manufacturer name
         * @param sortOrder sort order
         */
        fun List<CarWithTrips>.getSortedByManufacturer(sortOrder: SortOrder): List<CarWithTrips> =
            if (sortOrder == SortOrder.DESCENDING) this.sortedByDescending { it.manufacturer } else this.sortedBy { it.manufacturer }


        /**
         * @return Map<Int, List<Entities.CarWithTrips>> list grouped by year of car production
         */
        fun List<CarWithTrips>.getGroupedByYear(): Map<Int, List<CarWithTrips>> =
            this.groupBy { it.year }

        /**
         * Returns the number of items match the given [predicate]
         */
        fun List<CarWithTrips>.getQuantityBy(predicate: (CarWithTrips) -> Boolean): Int  =
            this.count(predicate)
    }
}