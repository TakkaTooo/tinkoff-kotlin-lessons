import java.lang.ArithmeticException
import kotlin.math.sqrt

fun Double.getSqrt(): Double {
    if (this < 0) {
        throw ArithmeticException("Can't extract square root of negative number")
    }
    return sqrt(this)
}