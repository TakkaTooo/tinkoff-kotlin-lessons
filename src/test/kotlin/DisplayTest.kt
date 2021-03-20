import io.mockk.every
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

    @Test
    fun `display init test`() {
        val slotA = slot<Int>()
        val slotB = slot<Int>()
        val display = mockk<Display> {
            every { id } returns 1
            every { diagonal } returns 24.0
            every { width } returns 2560
            every { height } returns 1440
            every { getPPI() } returns 122.0
            every { drawPoint(capture(slotA), capture(slotB)) } returns "Point was drawn on the coordinates (128, 256)"
        }

        val ppiResult = display.getPPI()
        val drawnResult = display.drawPoint(128, 256)

        with(display) {
            assertAll(
                "The display must have the following characteristics:",
                { assertEquals(1, id) },
                { assertEquals(128, slotA.captured) },
                { assertEquals(256, slotB.captured) },
                { assertEquals(24.0, diagonal) },
                { assertEquals(2560, width) },
                { assertEquals(1440, height) },
                { assertEquals(ppiResult, getPPI()) },
                { assertNotNull(drawnResult) }
            )
        }
    }
}