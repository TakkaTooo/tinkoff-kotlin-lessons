package pools

import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger

class ProcessingBlock : Callable<Long> {
    override fun call(): Long {
        val startTime = System.nanoTime()
        var x = AtomicInteger(0)
        while (x.get() < 1_000_000) {
            x.incrementAndGet()
        }
        return System.nanoTime() - startTime
    }
}