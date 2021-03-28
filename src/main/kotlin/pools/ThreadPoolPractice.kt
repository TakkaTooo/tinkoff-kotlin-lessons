package pools

import java.util.*
import java.util.concurrent.Executors

fun main() {
    val pools = listOf(
        Executors.newFixedThreadPool(10),
        Executors.newFixedThreadPool(20),
        Executors.newFixedThreadPool(30))
    val results = mutableMapOf<Int, Long>()

    var size = 10
    pools.forEach {
        it.submit(ProcessingBlock()).also { result ->
            results[size] = result.get()
        }
        it.shutdown()
        size += 10
    }

    val sortResults = results.toList().sortedBy { it.second }.toMap()
    sortResults.forEach {
        println("${it.key} : ${it.value}")
    }
}

