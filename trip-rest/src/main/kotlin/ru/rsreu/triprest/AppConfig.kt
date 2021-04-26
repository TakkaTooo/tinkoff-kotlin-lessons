package ru.rsreu.triprest

data class AppConfig(
    val http: HttpConfig,
    val db: DatabaseConfig,
    val carRest: CarRestConfig
)


data class HttpConfig(val port: Int)

data class DatabaseConfig(val url: String, val user: String, val password: String)

data class CarRestConfig(val url: String)