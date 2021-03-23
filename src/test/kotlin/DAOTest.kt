import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DAOTest {
    @MockK
    lateinit var dao: DAO
    private val slot = slot<Int>()

    @Test
    fun `getById dao func test with returning instance of Display`() {
        val display = display {
            this.id = 2
            diagonal = MyDouble(21.5)
            width = 1920
            height = 1080
        }
        dao = mockk {
            every { getById(capture(slot)) } returns display
        }

        val result = dao.getById(2)

        assertEquals(2, slot.captured)
        assertEquals(display, result)
        verify { dao.getById(2) }

    }

    @Test
    fun `geyById dao func first test with returning null, because does not exists user with id = 6`() {
        dao = mockk {
            every { getById(capture(slot)) } returns null
        }
        val result = dao.getById(6)

        assertEquals(6, slot.captured)
        assertNull(result)
        verify { dao.getById(6) }
    }

    @Test
    fun `geyById dao func second test with returning null because does not exists user with id = -1`() {
        dao = mockk {
            every { getById(capture(slot)) } returns null
        }
        val result = dao.getById(-1)

        assertEquals(-1, slot.captured)
        assertNull(result)
        verify { dao.getById(-1) }
    }

    @Test
    fun `getAll dao func test with returning all displays from emulated db`() {
        val displays = listOf(
            display {
                id = 1
                diagonal = MyDouble(27.0)
                width = 2560
                height = 1440
            },
            display {
                id = 2
                diagonal = MyDouble(24.0)
                width = 1920
                height = 1080
            })
        dao = mockk {
            every { getAll() } returns displays
        }
        val result = dao.getAll()

        assertEquals(displays, result)
        verify { dao.getAll() }
    }
}