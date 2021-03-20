import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "me.maxik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.1")

    testImplementation("io.mockk:mockk:1.10.6")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}