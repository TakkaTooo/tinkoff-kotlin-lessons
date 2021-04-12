package ru.rsreu.astashkin09.api

class ExceptionMessageFormer {
    companion object {
        fun formExceptionMessage(entityName: String, id: Int) = "$entityName with id = $id not exits"
    }
}