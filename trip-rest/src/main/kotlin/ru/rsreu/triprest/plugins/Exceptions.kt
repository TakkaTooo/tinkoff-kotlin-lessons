package ru.rsreu.triprest.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureExceptions() {
    install(io.ktor.features.StatusPages) {
        exception<NotFoundException> { cause ->
            call.respond(HttpStatusCode.NotFound, cause.message ?: "")
        }
    }
}