enum class Operation(val func: (Double, Double) -> Double) {
    ADDITIONAL({ first, second ->
        first + second
    }),
    SUBTRACTION({ first, second ->
        first - second
    }),
    MULTIPLICATION({ first, second ->
        first * second
    }),
    DIVISION({ first, second ->
        first / second

    })
}

class MyDouble(private val value: Double) {
    fun getByPredicate(predicate: (Double) -> Boolean): Boolean = predicate(value)

    fun getOperationResult(operand: Double, operation: Operation): Double =
        operation.func(value, operand)
}