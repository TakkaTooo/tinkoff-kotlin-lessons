package ru.rsreu.astashkin09

import org.slf4j.LoggerFactory

class TripLogger {
    companion object {
        private val logger = LoggerFactory.getLogger(javaClass)

        fun log(message: String) {
            logger.info(message)
        }
    }
}