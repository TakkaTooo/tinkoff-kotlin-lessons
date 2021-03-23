import kotlin.math.pow

class Display {
    var id: Int = 0
    var diagonal: MyDouble = MyDouble(0.0)
    var width: Int = 0
    var height: Int = 0

    fun getPPI(): Double =
        diagonal.getOperationResult(1 / (width.toDouble().pow(2) + height.toDouble().pow(2)).getSqrt(),
            Operation.MULTIPLICATION)

    fun drawPoint(x: Int, y: Int): String =
        "Point was drawn on the coordinates ($x, $y)"

    override fun toString(): String {
        return "${diagonal.toString()}: ${width.toString()}x${height.toString()}"
    }
}

fun display(block: Display.() -> Unit): Display = Display().apply(block)

