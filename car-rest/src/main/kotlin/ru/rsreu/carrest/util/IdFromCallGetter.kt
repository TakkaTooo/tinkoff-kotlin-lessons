package ru.rsreu.carrest.util

import io.ktor.application.*

class IdFromCallGetter {
    companion object {
        fun getIdFromCall(call: ApplicationCall) = call.parameters["id"]?.toInt() ?: -1
    }
}