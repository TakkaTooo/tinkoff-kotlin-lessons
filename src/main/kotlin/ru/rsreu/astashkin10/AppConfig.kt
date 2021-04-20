package ru.rsreu.astashkin10

data class AppConfig(
    val http: HttpConfig,
    val db: DatabaseConfig
)


data class HttpConfig(val port: Int)

data class DatabaseConfig(val url: String, val user: String, val password: String)