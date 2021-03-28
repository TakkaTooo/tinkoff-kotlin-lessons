import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.ArithmeticException

class DoubleExtensionsTest {

    @Test
    fun `getSqrt extension Double function test, extracting the root of 16 = 4`() {
        val result = 16.0.getSqrt()

        assertEquals(4.0, result)
    }

    @Test
    fun `getSqrt extension Double function test, extracting the root of 25 = 5`() {
        val result = 25.0.getSqrt()

        assertEquals(5.0, result)
    }

    @Test
    fun `getSqrt extension Double function test with throwing ArithmeticException (sqrt of -16 - NaN)`() {
        assertThrows<ArithmeticException> {
            (-16.0).getSqrt()
        }
    }

    @Test
    fun `getSqrt extension Double fun test with throwing ArithmeticException (sqrt of -1337 - NaN)`() {
        val msg = assertThrows<ArithmeticException> {
            (-1337.0).getSqrt()
        }.message

        assertEquals("Can't extract square root of negative number", msg)
    }
}