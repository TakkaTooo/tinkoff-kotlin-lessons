package ru.rsreu.carrest.util

class ErrorMessageGenerator() {
    companion object {
        fun generateErrorMessage(modelName: String, id: Int) = "$modelName with id = $id not found"
    }
}
