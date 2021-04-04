import dao.CarDAO
import dao.DriverDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 *
 */
class TripLogGenerator(private val carId: Int, var channel: Channel<String>) {

    suspend fun generateTripLog() {
        val car = CarDAO.getCarById(carId)
        while (true) {
            channel.send("Driver with id = ${car?.driverId} made the trip on car ${car?.manufacturer} at a distance of ${
                Random.nextInt(1, 20)
            } km.")
            delay(1000L)
        }

    }
}