package ru.rsreu.astashkin09.api

import io.swagger.annotations.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.rsreu.astashkin09.dao.CarDao
import ru.rsreu.astashkin09.dao.TripDao
import ru.rsreu.astashkin09.model.Trip
import ru.rsreu.astashkin09.model.TripWithCar
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/trip")
class TripController {
    @ApiOperation("Returns a list of entities of type TripWithCar")
    @GetMapping
    fun getTripsWithCars(): List<TripWithCar> {
        val trips = TripDao.getTrips()
        return trips.map { trip ->
            val car = CarDao.getCarById(trip.carId)
            TripWithCar(trip.id, trip.carId, car?.manufacturer ?: "", car?.year ?: 0)
        }
    }

    @ApiOperation("Returns the TripWithCar entity by id")
    @ApiResponses(ApiResponse(code = 200, message = "If the entity by id was successfully found and returned"),
        ApiResponse(code = 404, message = "Trip not found"))
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

    @ApiOperation("Add the entity of the trip. Return log add log.")
    @PostMapping
    fun addTrip(
        @ApiParam(type = "Trip",
            value = "the trip instance you want to create") @RequestBody trip: Trip,
    ): ResponseEntity<String> {
        return ResponseEntity("Trip has been added. Trip = $trip", HttpStatus.OK)
    }

    @ApiOperation("Updates the entity of the ride. Returns the update log.")
    @PutMapping("/{id}")
    fun updateTrip(
        @ApiParam("trip id where you want to update") @PathVariable id: Int,
        @ApiParam(type = "Trip",
            value = "the trip instance you want to update") @RequestBody trip: Trip,
    ): ResponseEntity<String> {
        return ResponseEntity("Trip has been updated. Trip = $trip, id = $id", HttpStatus.OK)
    }

    @ApiOperation("Removes the entity of the trip by id. Returns delete log.")
    @ApiResponses(ApiResponse(code = 200, message = "If the entity by id was successfully found and deleted"),
        ApiResponse(code = 404, message = "Trip not found"))
    @DeleteMapping("/{id}")
    fun deleteTrip(@ApiParam("trip id where you want to delete") @PathVariable id: Int): ResponseEntity<String> {
        val trip = TripDao.getTripById(id)
        if (trip != null) {
            return ResponseEntity("Trip has been deleted. id = $id", HttpStatus.OK)
        } else {
            throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Trip::class.simpleName!!, id))
        }
    }
}