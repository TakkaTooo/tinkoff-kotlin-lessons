package service

import jdbc.Client
import java.sql.SQLException

class CarsharingDropper {
    companion object {

        /**
         * Drops tables for carsharing service subject area.
         * @param client class-client for working with database.
         * @throws CarsharingServiceOperationFaultException in case if connection with db closed
         * or any table not exist in db
         */
        fun deleteAllTables(client: Client) {
            val sql = """
                DROP TABLE Trip;
                DROP TABLE DriversCars;
                DROP TABLE Car;
                DROP TABLE Driver;
            """.trimIndent()
            try {
                client.executeUpdate(sql)
            } catch (e: SQLException) {
                throw CarsharingServiceOperationFaultException(when (e.errorCode) {
                    0 -> CarsharingServiceErrorCode.CONNECTION_CLOSED
                    else -> CarsharingServiceErrorCode.TABLE_NOT_EXISTS
                })
            }
        }
    }

}