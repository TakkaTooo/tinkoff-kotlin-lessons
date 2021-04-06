package pools

import java.util.concurrent.Callable

class ProcessingBlock : Callable<Long> {
    override fun call(): Long {
        val startTime = System.nanoTime()
        while (counter < 1_000_000) {
            counter++
        }
        return System.nanoTime() - startTime
    }
}