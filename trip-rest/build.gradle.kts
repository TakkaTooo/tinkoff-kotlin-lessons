import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val config4k_version: String by project
val exposed_version: String by project
val flyway_version: String by project
val h2_version: String by project
val kodein_version: String by project

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.32"
}

group = "ru.rsreu"
version = "1.0"

application {
    mainClass.set("ru.rsreu.triprest.ApplicationKt")
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


tasks.register<Jar>("fatJar") {
    manifest {
        attributes["Main-Class"] = "ru.rsreu.triprest.ApplicationKt"
    }
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

dependencies {
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-gson:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.github.config4k:config4k:$config4k_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.flywaydb:flyway-core:$flyway_version")
    implementation("mysql:mysql-connector-java:8.0.24")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodein_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}