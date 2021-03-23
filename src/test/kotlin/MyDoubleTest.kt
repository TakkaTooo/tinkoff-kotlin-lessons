import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class MyDoubleTest {

    private val myDouble = MyDouble(5.0)

    @Test
    fun `getByPredicate should return 5 more than 0 - true`() {
        val result = myDouble.getByPredicate {
            it > 0
        }
        assertEquals(true, result)
    }

    @Test
    fun `getByPredicate should return 5 less than 0 - false`() {
        val result = myDouble.getByPredicate {
            it < 0
        }
        assertEquals(false, result)
    }

    @Test
    fun `getOperationResult with ADDTIONAL test (5 + 5 = 10)`() {
        val result = myDouble.getOperationResult(5.0, Operation.ADDITIONAL)
        assertEquals(10.0, result)
    }

    @Test
    fun `getOperationResult with ADDTIONAL test (5 + (-20) = -15)`() {
        val result = myDouble.getOperationResult(-20.0, Operation.ADDITIONAL)
        assertEquals(-15.0, result)
    }

    @Test
    fun `getOperationResult with SUBTRACTION test (5 - 1,5 = 3,5)`() {
        val result = myDouble.getOperationResult(1.5, Operation.SUBTRACTION)
        assertEquals(3.5, result)
    }

    @Test
    fun `getOperationResult with SUBTRACTION test (5 - 10 = -5)`() {
        val result = myDouble.getOperationResult(10.0, Operation.SUBTRACTION)
        assertEquals(-5.0, result)
    }

    @Test
    fun `getOperationResult with MULTIPLICATION test (5 * 3,56 = 17,8)`() {
        val result = myDouble.getOperationResult(3.56, Operation.MULTIPLICATION)
        assertEquals(17.8, result)
    }

    @Test
    fun `getOperationResult with MULTIPLICATION test (5 * (-2,8) = -14)`() {
        val result = myDouble.getOperationResult(-2.8, Operation.MULTIPLICATION)
        assertEquals(-14.0, result)
    }

    @Test
    fun `getOperationResult with DIVISION test (5 divide by 1 = 5)`() {
        val result = myDouble.getOperationResult(1.0, Operation.DIVISION)
        assertEquals(5.0, result)
    }

    @Test
    fun `getOperationResult with DIVISION test (5 divide by 2 = 2,5)`() {
        val result = myDouble.getOperationResult(2.0, Operation.DIVISION)
        assertEquals(2.5, result)
    }
}