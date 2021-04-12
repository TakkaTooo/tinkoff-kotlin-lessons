package ru.rsreu.astashkin09.api

class EntityNotExistsException(override val message: String?) : Exception(message)