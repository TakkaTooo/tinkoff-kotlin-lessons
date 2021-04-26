package ru.rsreu.triprest.trip

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import ru.rsreu.triprest.util.ErrorMessageGenerator
import ru.rsreu.triprest.util.IdFromCallGetter

fun Application.tripModule(carRestUrl: String) {
    val service: TripService by closestDI().instance()

    routing {
        route("/trip") {
            get {
                call.respond(service.findAll())
            }
        }
        route("/trip/{id}") {
            get {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    call.respond(service.findById(id))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Trip", id))
                }
            }
        }
        route("/tripwithcar/{id}") {
            get {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    val trip = service.findById(id)
                    val client = HttpClient(CIO) {
                        install(JsonFeature)
                    }
                    val car = client.get<Car>("$carRestUrl/car/${trip.carId}")
                    call.respond(TripWithCar(
                        trip.id,
                        trip.carId,
                        car.manufacturer,
                        car.year,
                        trip.distance
                    ))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Trip", id))
                }
            }
        }
    }
}

fun DI.Builder.tripComponents() {
    bind<TripDao>() with singleton { TripDao(instance()) }
    bind<TripService>() with singleton { TripService(instance(), instance()) }
}

@Serializable
data class CreateTripRequest(val carId: Int, val distance: Int)

@Serializable
data class UpdateTripRequest(val id: Int, val carId: Int, val distance: Int)

@Serializable
data class SwitchCarOnTripRequest(val carId: Int)