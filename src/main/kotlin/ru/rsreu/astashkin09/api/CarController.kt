package ru.rsreu.astashkin09.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.rsreu.astashkin09.dao.CarDao
import ru.rsreu.astashkin09.model.Car

@RestController
@RequestMapping("/car")
class CarController {
    @ApiOperation("Returns the Car entity by id")
    @ApiResponses(
            ApiResponse(code = 200, message = "If the entity by id was successfully found and returned"),
            ApiResponse(code = 404, message = "Car not found"),
            ApiResponse(code = 500, message = "Internal error"))
    @GetMapping("/{id}")
    fun getCarById(@ApiParam("id where you want to return the car") @PathVariable id: Int): Car {
        val car = CarDao.getCarById(id)
        if (car != null) {
            return car
        } else {
            throw EntityNotExistsException(ExceptionMessageFormer.formExceptionMessage(Car::class.simpleName!!, id))
        }
    }
}