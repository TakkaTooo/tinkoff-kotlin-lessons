import java.io.Closeable
import java.sql.*

/**
 * The class that creates a connection to the database by url.
 * Methods send queries to the database
 */
class Client(private val url: String) {

    private val conn: Connection = DriverManager.getConnection("jdbc:sqlite:$url")

    /**
     * The method sending the update request
     * (INSERT INTO,
     *  UPDATE,
     *  DELETE,
     *  DROP,
     *  etc.)
     *  @param sql sql query
     *  @param args strings arguments-parameters for inserting to sql query
     *  @return the number of rows returned by the query
     *  @throws SQLException in cases foreseen by [PrepareStatement.executeUpdate()]
     */
    @Throws(SQLException::class)
    fun executeUpdate(sql: String, vararg args: String): Int {
        val result: Int
        if (args.isEmpty()) {
            conn.createStatement().use {
                result = it.executeUpdate(sql)
            }
        } else {
            conn.prepareStatement(sql).use {
                args.mapIndexed { index: Int, item: String ->
                    Pair(index, item)
                }.forEach { pair ->
                    it.setString(pair.first, pair.second)
                }
                result = it.executeUpdate()
            }
        }
        return result
    }

    /**
     * Method sending fetch requests
     * @param sql sql query
     * @param args strings arguments-parameters for inserting to sql query
     * @return HashMap where key is column name and value is value of column
     * @throws SQLException if no rows are found in the database
     */
    @Throws(SQLException::class)
    fun executeQuery(sql: String, vararg args: String): List<HashMap<String, Any>> {
        val resultList = mutableListOf<HashMap<String, Any>>()
        conn.prepareStatement(sql).use {
            if (args.isNotEmpty()) {
                args.mapIndexed { index: Int, item: String ->
                    Pair(index, item)
                }.forEach { pair ->
                    it.setString(pair.first + 1, pair.second)
                }
            }
            val res = it.executeQuery()
            val resMetadata = it.metaData
            val columnCount = resMetadata.columnCount
            while (res.next()) {
                val resMap: HashMap<String, Any> = HashMap()
                var i = 1
                while (i <= columnCount) {
                    resMap[resMetadata.getColumnName(i)] = res.getObject(i++)
                }
                resultList.add(resMap)
            }
        }
        if (resultList.isEmpty()) {
            throw SQLException("No rows found in the database")
        }
        return resultList
    }
}