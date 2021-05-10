package ru.rsreu.astashkin10.trip

import io.ktor.application.*
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
import ru.rsreu.astashkin10.car.CarService
import ru.rsreu.astashkin10.util.ErrorMessageGenerator
import ru.rsreu.astashkin10.util.IdFromCallGetter
//TODO: REFACTORING reps in methods

fun Application.tripModule() {
    val service: TripService by closestDI().instance()
    val carService: CarService by closestDI().instance()

    routing {
        route("/trip") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateTripRequest>()
                call.respond(service.create(request.carId, request.distance))
            }
            patch {
                val request = call.receive<UpdateTripRequest>()
                runCatching {
                    carService.findById(request.carId)
                }.onSuccess {
                    val rowCount = service.update(request.id, request.carId, request.distance)
                    if (rowCount != 0) {
                        call.respond(rowCount)
                    } else {
                        throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Trip", request.id))
                    }
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", request.carId))
                }
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
            patch {
                val request = call.receive<SwitchCarOnTripRequest>()
                runCatching {
                    carService.findById(request.carId)
                }.onSuccess {
                    val id = IdFromCallGetter.getIdFromCall(call)
                    val rowCount = service.changeCar(id, request.carId)
                    if (rowCount != 0) {
                        call.respond(rowCount)
                    } else {
                        throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Trip", id))
                    }
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", request.carId))
                }
            }
            delete {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    call.respond(service.delete(id))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Trip", id))
                }
            }
        }
        route("/trip/car/{id}") {
            get {
                val carId = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    carService.findById(carId)
                }.onSuccess {
                    call.respond(service.findByCarId(carId))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", carId))
                }
            }
        }
    }
}

fun DI.Builder.tripComponents() {
    bind<TripDao>() with singleton { TripDao(instance()) }
    bind<TripService>() with singleton { TripService(instance()) }
}

@Serializable
data class CreateTripRequest(val carId: Int, val distance: Int)

@Serializable
data class UpdateTripRequest(val id: Int, val carId: Int, val distance: Int)

@Serializable
data class SwitchCarOnTripRequest(val carId: Int)