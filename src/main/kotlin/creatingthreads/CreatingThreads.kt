package creatingthreads

import kotlin.concurrent.thread

fun main() {
    // With Thread
    Thread {
        println("Print from thread: ${Thread.currentThread().name}")
    }.apply {
        start()
        join()
    }

    // With Runnable
    Thread(PrintRunnable()).apply {
        start()
        join()
    }

    // With DSL
    thread {
        println("Print from thread from dsl: ${Thread.currentThread().name}")
    }.join()

    // Daemon
    thread(start = false, isDaemon = true) {
        println("Print from daemon thread: ${Thread.currentThread().name}, isDaemon = ${Thread.currentThread().isDaemon}")
    }.apply {
        start()
        join()
    }

    // With priorities
    val firstThread = thread(start = false, priority = 2) {
        blockPriority()
    }
    val secondThread = thread(start = false, priority = 10) {
        blockPriority()
    }
    val thirdThread = Thread {
        blockPriority()
    }
    thirdThread.priority = 6

    firstThread.start()
    secondThread.start()
    thirdThread.start()
}

fun blockPriority() {
    println("Print from thread with priority: ${Thread.currentThread().name} : ${Thread.currentThread().priority}")
}
