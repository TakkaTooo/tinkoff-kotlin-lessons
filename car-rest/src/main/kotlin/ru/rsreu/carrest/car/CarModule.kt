package ru.rsreu.carrest.car

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import ru.rsreu.carrest.util.ErrorMessageGenerator
import ru.rsreu.carrest.util.IdFromCallGetter

fun Application.carModule() {
    val service: CarService by closestDI().instance()

    routing {
        route("/car") {
            get {
                call.respond(service.findAll())
            }
        }
        route("/car/{id}") {
            get {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    call.respond(service.findById(id))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", id))
                }
            }
        }
    }
}

fun DI.Builder.carComponents() {
    bind<CarDao>() with singleton { CarDao(instance()) }
    bind<CarService>() with singleton { CarService(instance(), instance()) }
}

@Serializable
data class CreateCarRequest(val manufacturer: String, val year: Int)

@Serializable
data class UpdateCarRequest(val id: Int, val manufacturer: String, val year: Int)