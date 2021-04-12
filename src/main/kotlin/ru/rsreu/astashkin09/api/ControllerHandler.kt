package ru.rsreu.astashkin09.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.lang.NullPointerException

@ControllerAdvice
class ControllerHandler : NullPointerException() {

    @ExceptionHandler(EntityNotExistsException::class)
    fun handleException(ex: EntityNotExistsException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }
}