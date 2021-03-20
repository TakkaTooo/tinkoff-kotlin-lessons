import kotlin.math.pow

class Display {
    var id: Int? = null
    var diagonal: Double? = null
    var width: Int? = null
    var height: Int? = null

    fun getPPI(): Double =
        (width!!.toDouble().pow(2.0) + height!!.toDouble().pow(2.0)).getSqrt() / diagonal!!

    fun drawPoint(x: Int, y: Int): String =
        "Point was drawn on the coordinates ($x, $y)"

    override fun toString(): String {
        return "${diagonal.toString()}: ${width.toString()}x${height.toString()}"
    }
}

fun display(block: Display.() -> Unit): Display = Display().apply(block)

