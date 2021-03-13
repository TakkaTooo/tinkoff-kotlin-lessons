import org.sqlite.SQLiteException

class Initializer {

    companion object {
        fun createTables(client: Client) {
            val sql = """
                CREATE TABLE Car(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    manufacturer TEXT NOT NULL,
                    year INTEGER NOT NULL CHECK(year > 2010)
                );
                CREATE TABLE Driver(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    drivingLicence NOT NULL UNIQUE
                );            
                CREATE TABLE Trip(
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    carId INTEGER NOT NULL,
                    driverId INTEGER NOT NULL,
                    distance INTEGER NOT NULL,
                    FOREIGN KEY (carId) REFERENCES Car(id),
                    FOREIGN KEY (driverId) REFERENCES Driver(id)
                );
                CREATE TABLE DriversCars(
                    driverId INTEGER NOT NULL,
                    carId INTEGER NOT NULL,
                    FOREIGN KEY (carId) REFERENCES Car(id),
                    FOREIGN KEY (driverId) REFERENCES Driver(id)
                );
            """.trimIndent()
            client.executeUpdate(sql)
        }

        fun fillTables(client: Client) {
            val sql = """
                INSERT INTO Car (manufacturer, year) 
                VALUES
                    ("Renault", 2015),
                    ("Hyundai", 2015),
                    ("Renault", 2018),
                    ("VAZ", 2018),
                    ("BMW", 2018),
                    ("Tesla", 2021),
                    ("Audi", 2021);
                INSERT INTO Driver(name, drivingLicence)
                VALUES
                    ("Pavel", "123456"),
                    ("Alex", "212323"),
                    ("Oleg", "1337000000"),
                    ("Gabe", "333333"),
                    ("Pavel", "000000001");
                INSERT INTO Trip(carId, driverId, distance)
                VALUES
                    (1, 1, 15),
                    (1, 2, 10),
                    (1, 3, 13),
                    (2, 5, 10),
                    (2, 5, 19),
                    (3, 3, 5),
                    (4, 7, 2),
                    (4, 4, 25),
                    (5, 4, 16);
                INSERT INTO DriversCars
                VALUES 
                    (1, 1),
                    (2, 1),
                    (3, 1),
                    (5, 2),
                    (3, 3),
                    (7, 4),
                    (4, 4),
                    (4, 5);
            """.trimIndent()
            client.executeUpdate(sql)
        }

        @Throws(SQLiteException::class)
        fun deleteAllTables(client: Client) {
            val sql = """
                DROP TABLE Trip;
                DROP TABLE DriversCars;
                DROP TABLE Car;
                DROP TABLE Driver;
            """.trimIndent()
            client.executeUpdate(sql)
        }
    }
}