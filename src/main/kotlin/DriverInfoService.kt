import dao.CarDAO
import dao.DriverDAO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import model.Car
import model.Driver
import model.DriverWithCars

/**
 * A service that receives information from 2 other services asynchronously and compiles this information into 1 object
 */
class DriverInfoService {
    companion object {
        suspend fun getInfoAboutDriverAndCars(id: Int): DriverWithCars = runBlocking {
            val driverJob = async { getDriverById(id) }
            val carsJob = async { getAllCars() }
            val driver = driverJob.await()
            println("Driver information received")
            val cars = carsJob.await()
            println("Cars information received")
            DriverWithCars(
                driver?.id ?: -1,
                driver?.name ?: "",
                driver?.drivingLicence ?: "",
                cars.filter { it.driverId == id }.map { it.id }
            )
        }

        private suspend fun getAllCars(): List<Car> {
            delay(1000)
            return CarDAO.getAllCars()
        }

        private suspend fun getDriverById(id: Int): Driver? {
            delay(1000)
            return DriverDAO.getDriverById(id)
        }
    }

}