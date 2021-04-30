package ru.rsreu.astashkin09.api

import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import ru.rsreu.astashkin09.TripLogger
import ru.rsreu.astashkin09.dao.CarDao
import ru.rsreu.astashkin09.dao.TripDao
import ru.rsreu.astashkin09.model.Car
import ru.rsreu.astashkin09.model.Trip
import ru.rsreu.astashkin09.model.TripWithCar

@RestController
@RequestMapping("/trip")
class TripController {
    private val webClient = WebClient.create("http://localhost:8080/")

    @ApiOperation("Returns a list of entities of type TripWithCar")
    @ApiResponses(
            ApiResponse(code = 200, message = "If trips were found and returned",
                    response = TripWithCar::class, responseContainer = "List"),
            ApiResponse(code = 404, message = "If songs not found"),
            ApiResponse(code = 500, message = "Internal error")
    )
    @GetMapping
    fun getTripsWithCars(): List<TripWithCar> {
        val trips = TripDao.getTrips()
        return trips.map { trip ->
            val car = getCarById(trip.carId)
                    ?: throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Car::class.simpleName!!,
                            trip.carId))
            TripWithCar(trip.id, trip.carId, car.manufacturer, car.year)
        }
    }

    @ApiOperation("Returns the TripWithCar entity by id")
    @ApiResponses(
            ApiResponse(code = 200, message = "If the entity by id was successfully found and returned"),
            ApiResponse(code = 404, message = "Trip not found"),
            ApiResponse(code = 500, message = "Internal error"))
    @GetMapping("/{id}")
    fun getTripById(@ApiParam("id where you want to return the trip") @PathVariable id: Int): TripWithCar {
        val trip = TripDao.getTripById(id)
        if (trip != null) {
            val car = CarDao.getCarById(trip.carId)
            return TripWithCar(trip.id, trip.carId, car?.manufacturer ?: "", car?.year ?: 0)
        } else {
            throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Trip::class.simpleName!!, id))
        }
    }

    @ApiOperation("Updates the entity of the ride. Returns the update log.")
    @ApiResponses(
            ApiResponse(code = 200, message = "If the entity by id was successfully found and updated"),
            ApiResponse(code = 404, message = "Trip not found"),
            ApiResponse(code = 500, message = "Internal error"))
    @PutMapping("/{id}")
    fun updateTrip(
            @ApiParam("trip id where you want to update") @PathVariable id: Int,
            @ApiParam(type = "Trip",
                    value = "the trip instance you want to update") @RequestBody trip: Trip,
    ): ResponseEntity<String> {
        if (TripDao.getTripById(id) != null) {
            TripLogger.log("Update trip with id $id")
            return ResponseEntity(id.toString(), HttpStatus.OK)
        } else {
            TripLogger.log("Trip with id = $id not found")
            throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Trip::class.simpleName!!, id))
        }
    }

    @ApiOperation("Add the entity of the trip. Return log add log.")
    @ApiResponses(ApiResponse(code = 200, message = "If the entity successfully added"))
    @PostMapping
    fun addTrip(
            @ApiParam(type = "Trip",
                    value = "the trip instance you want to create") @RequestBody trip: Trip,
    ): ResponseEntity<String> {
        TripLogger.log("Add trip with id = ${trip.id}")
        return ResponseEntity(trip.id.toString(), HttpStatus.OK)
    }

    @ApiOperation("Removes the entity of the trip by id. Returns delete log.")
    @ApiResponses(
            ApiResponse(code = 200, message = "If the entity by id was successfully found and deleted"),
            ApiResponse(code = 404, message = "Trip not found"),
            ApiResponse(code = 500, message = "Internal error"))
    @DeleteMapping("/{id}")
    fun deleteTrip(@ApiParam("trip id where you want to delete") @PathVariable id: Int): ResponseEntity<String> {
        val trip = TripDao.getTripById(id)
        if (trip != null) {
            TripLogger.log("Delete car with id = $id")
            return ResponseEntity(id.toString(), HttpStatus.OK)
        } else {
            TripLogger.log("Trip with id = $id not found")
            throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Trip::class.simpleName!!, id))
        }
    }

    private fun getCarById(id: Int): Car? {
        return webClient.get().uri("/car/$id").retrieve().bodyToMono(Car::class.java).block()
    }
}