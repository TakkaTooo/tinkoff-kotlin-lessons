package service

import jdbc.Client
import java.sql.SQLException

class CarsharingDropper {
    companion object {

        /**
         * Used because SQLException returns the same codes
         * for Table already exists and Table not exists
         */
        private const val errorCodeOffset = 1

        /**
         * Drops tables for carsharing service subject area.
         * @param client class-client for working with database.
         * @throws CarsharingServiceOperationFaultException in case if connection with db closed
         * or any table not exist in db
         */
        @Throws(CarsharingServiceOperationFaultException::class)
        fun dropAllTables(client: Client) {
            val sql = """
                DROP TABLE Trip;
                DROP TABLE DriversCars;
                DROP TABLE Car;
                DROP TABLE Driver;
            """.trimIndent()
            try {
                client.executeUpdate(sql)
            } catch (e: SQLException) {
                println("dropAllTable: exception on dropping all tables")
                throw CarsharingServiceOperationFaultException(
                    CarsharingServiceErrorCode.getInstanceByCode(e.errorCode + errorCodeOffset))
            }
        }
    }

}