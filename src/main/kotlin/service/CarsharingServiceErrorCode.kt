package service

enum class CarsharingServiceErrorCode(val code: Int, val message: String) {
    CONNECTION_CLOSED(0, "Database connection closed"),
    TABLE_ALREADY_EXISTS(1, "Table already exists"),
    CONSTRAINT_FAILED(19, "Constraint failed"),
    TABLE_NOT_EXISTS(2, "Such table not exists");

    companion object {
        /**
         * Returns CarsharingServiceErrorCode "instance" by code
         */
        fun getInstanceByCode(c: Int): CarsharingServiceErrorCode =
            values().first { it.code == c }

    }
}