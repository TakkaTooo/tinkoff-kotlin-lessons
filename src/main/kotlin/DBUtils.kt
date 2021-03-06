/**
 * Enum class describing 2 sorting orders
 */
enum class SortOrder {
    ASCENDING, DESCENDING
}

class DBUtility {
    companion object {
        /**
         * Collects data from the CarsDBO class and converts it into a list of CarWithTrips using the data obtained from the CarTripsDBO class
         */
        fun getCarsWithTrips(): List<CarWithTrips> = CarsDBO.getAllCars().map {
            val carTrips = CarTripsDBO.getTripsByCarId(it.id)
            CarWithTrips(it.id, it.manufacturer, it.year, carTrips?.trips, carTrips?.isAvailable)
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
        fun List<CarWithTrips>.getSortedByManufacturer(sortOrder: SortOrder): List<CarWithTrips> {
            val sortedList = this.sortedBy { it.manufacturer }
            return if (sortOrder == SortOrder.DESCENDING) sortedList.reversed() else sortedList

        }

        /**
         * @return Map<Int, List<CarWithTrips>> list grouped by year of car production
         */
        fun List<CarWithTrips>.getGroupedByYear(): Map<Int, List<CarWithTrips>> =
            this.groupBy { it.year }

        /**
         * Returns the number of items match the given [predicate]
         */
        fun List<CarWithTrips>.getQuantityOf(predicate: (CarWithTrips) -> Boolean): Int {
            if (isEmpty()) return 0
            var quantity: Int = 0
            for (element in this) if (predicate(element)) quantity++
            return quantity
        }
    }
}