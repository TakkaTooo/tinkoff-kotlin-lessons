import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel


fun main() = runBlocking {
    println(DriverInfoService.getInfoAboutDriverAndCars(1))

    val linkingChannel = Channel<String>()
    val tripLogGenerator = TripLogGenerator(3, linkingChannel)
    val tripLogReceiver = TripLogReceiver(linkingChannel)

    val tripLoggerJob = launch {
        tripLogGenerator.generateTripLog()
    }

    repeat(5) {
        println(tripLogReceiver.receiveTripLog())
    }
    tripLoggerJob.cancelAndJoin()
}