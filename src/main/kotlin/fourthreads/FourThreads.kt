package fourthreads

import kotlin.concurrent.thread

fun main() {
    val o = Resource()
    repeat(5) {
        thread(name = "Counter $it") {
            println("${Thread.currentThread().name}: I increased the resource value: ${++o.x}")
        }
        thread(name = "Checker 1") {
            println("${Thread.currentThread().name}: I check the resource value: ${o.x}")
        }
        thread(name = "Checker 2") {
            println("${Thread.currentThread().name}: I check the resource value: ${o.x}")
        }
        thread(name = "Checker 3") {
            println("${Thread.currentThread().name}: I check the resource value: ${o.x}")
        }
    }
}

