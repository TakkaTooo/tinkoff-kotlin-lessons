import kotlinx.coroutines.channels.Channel

class TripLogReceiver(var channel: Channel<String>) {
    suspend fun receiveTripLog(): String {
        return channel.receive()
    }
}