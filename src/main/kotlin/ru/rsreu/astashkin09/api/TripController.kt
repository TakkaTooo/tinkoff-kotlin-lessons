package ru.rsreu.astashkin09.api

import org.springframework.web.bind.annotation.*
import ru.rsreu.astashkin09.dao.TripDao
import ru.rsreu.astashkin09.model.Trip


@RestController
@RequestMapping("/trip")
class TripController {
    @GetMapping
    fun getTrips(): List<Trip> = TripDao.getTrips()

    @PostMapping
    fun addTrip(@RequestBody trip: Trip) {
        println("Trip was added. Trip = $trip")
    }
}