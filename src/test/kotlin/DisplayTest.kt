import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DisplayTest {
    @MockK
    private lateinit var display: Display

    private val slotX = slot<Int>()
    private val slotY = slot<Int>()

    @Test
    fun `display init test`() {
        display = mockk {
            every { id } returns 1
            every { diagonal } returns MyDouble(24.0)
            every { width } returns 2560
            every { height } returns 1440
            every { getPPI() } returns 122.0
            every { drawPoint(capture(slotX), capture(slotY)) } returns "Point was drawn on the coordinates (128, 256)"
        }

        val ppiResult = display.getPPI()
        val drawnResult = display.drawPoint(128, 256)

        with(display) {
            assertAll(
                "The display must have the following characteristics:",
                { assertEquals(1, id) },
                { assertEquals(128, slotX.captured) },
                { assertEquals(256, slotY.captured) },
                { assertEquals(24.0, diagonal.value) },
                { assertEquals(2560, width) },
                { assertEquals(1440, height) },
                { assertEquals(ppiResult, getPPI()) },
                { assertNotNull(drawnResult) }
            )
        }
    }
}