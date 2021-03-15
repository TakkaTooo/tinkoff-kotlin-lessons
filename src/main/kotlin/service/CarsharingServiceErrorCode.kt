package service

enum class CarsharingServiceErrorCode(val code: Int, val message: String) {
    CONNECTION_CLOSED(0, "Database connection closed"),
    TABLE_ALREADY_EXISTS(1, "Table already exists"),
    CONSTRAINT_FAILED(2, "Constraint failed"),
    TABLE_NOT_EXISTS(3, "Such table not exists")

}