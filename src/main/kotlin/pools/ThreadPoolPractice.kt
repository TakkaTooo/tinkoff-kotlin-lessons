package pools

import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

@Volatile
var counter: Int = 0

fun main() {
    val pools = listOf(
        Executors.newFixedThreadPool(10),
        Executors.newFixedThreadPool(20),
        Executors.newFixedThreadPool(30)
    )
    val results = mutableMapOf<Int, Long>()

    pools.forEach { executor ->
        counter = 0
        var workingTime: Long = 0
        repeat((executor as ThreadPoolExecutor).corePoolSize) {
            executor.submit(ProcessingBlock()).also { result ->
                workingTime += result.get()
            }
        }
        results[executor.corePoolSize] = workingTime
        executor.shutdown()
    }

    val sortResults = results.toList().sortedBy { it.second }.toMap()
    sortResults.forEach {
        println("${it.key} : ${it.value}")
    }
}


