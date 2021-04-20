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


private fun getIdFromCall(call: ApplicationCall) = call.parameters["id"]?.toInt() ?: -1

private fun generateErrorMessage(call: ApplicationCall) = "Car with id = ${getIdFromCall(call)} not found."

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
                runCatching {
                    val request = call.receive<UpdateCarRequest>()
                    call.respond(service.update(request.id, request.manufacturer, request.year))
                }.onFailure {
                    it.printStackTrace()
                    throw NotFoundException(generateErrorMessage(call))
                }
            }
        }
        route("/cars/{id}") {
            get {
                runCatching {
                    call.respond(service.findById(getIdFromCall(call)))
                }.onFailure {
                    throw NotFoundException(generateErrorMessage(call))
                }
            }

            delete {
                runCatching {
                    call.respond(service.delete(getIdFromCall(call)))
                }.onFailure {
                    throw NotFoundException(generateErrorMessage(call))
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