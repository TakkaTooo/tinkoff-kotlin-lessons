package ru.rsreu.astashkin10.car

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
import ru.rsreu.astashkin10.util.ErrorMessageGenerator
import ru.rsreu.astashkin10.util.IdFromCallGetter

fun Application.carModule() {
    val service: CarService by closestDI().instance()

    routing {
        route("/cars") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateCarRequest>()
                call.respond(service.create(request.manufacturer, request.year))
            }
            patch {
                val request = call.receive<UpdateCarRequest>()
                val rowCount = service.update(request.id, request.manufacturer, request.year)
                if (rowCount != 0) {
                    call.respond(rowCount)
                } else {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", request.id))
                }
            }
        }
        route("/cars/{id}") {
            get {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    call.respond(service.findById(id))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", id))
                }
            }

            delete {
                val id = IdFromCallGetter.getIdFromCall(call)
                runCatching {
                    call.respond(service.delete(id))
                }.onFailure {
                    throw NotFoundException(ErrorMessageGenerator.generateErrorMessage("Car", id))
                }
            }
        }
    }
}

fun DI.Builder.carComponents() {
    bind<CarDao>() with singleton { CarDao(instance()) }
    bind<CarService>() with singleton { CarService(instance()) }
}

@Serializable
data class CreateCarRequest(val manufacturer: String, val year: Int)

@Serializable
data class UpdateCarRequest(val id: Int, val manufacturer: String, val year: Int)