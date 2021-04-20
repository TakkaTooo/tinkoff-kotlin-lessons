package ru.rsreu.astashkin10

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.jetbrains.exposed.sql.Database
import org.kodein.di.ktor.di
import ru.rsreu.astashkin10.car.carComponents
import ru.rsreu.astashkin10.car.carModule
import ru.rsreu.astashkin10.plugins.configureExceptions
import ru.rsreu.astashkin10.plugins.configureSerialization
import ru.rsreu.astashkin10.trip.tripComponents
import ru.rsreu.astashkin10.trip.tripModule

fun main() {
    val config = ConfigFactory.load().extract<AppConfig>()
    migrate(config.db)

    val engine = embeddedServer(Netty, port = config.http.port) {
        di {
            coreComponents(config)
            carComponents()
            tripComponents()
        }
        configureSerialization()
        configureExceptions()
        carModule()
        tripModule()
    }
    engine.start()

}

fun DI.Builder.coreComponents(config: AppConfig) {
    bind<AppConfig>() with singleton { config }
    bind<Database>() with singleton {
        Database.connect(
            url = config.db.url,
            user = config.db.user,
            password = config.db.password
        )
    }
}

fun migrate(db: DatabaseConfig) {
    Flyway
        .configure()
        .dataSource(db.url, db.user, db.password)
        .load()
        .migrate()
}