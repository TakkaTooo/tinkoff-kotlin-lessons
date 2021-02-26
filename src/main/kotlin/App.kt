fun main() {
    // Stack demonstration
    val stack = myStackOf<Int>()
    println(stack.isEmpty())
    stack.push(777)
    stack.push(1)
    println(stack.pop())
    println(stack.safePop())
    try {
        println(stack.pop())
    } catch (e: Exception) {
        println(e.toString())
    }

    val newStack = MyStack<Int>()
    newStack.push(1)
    println(newStack.pop())

    val newNewStack = myStackOf<Int>(1, 2, 3)
    newNewStack.push(0)
    println(newNewStack.pop())
    println(newNewStack.pop())
    newNewStack.iterator().withIndex().forEach {
        println("Item ${it.value} with index ${it.index}")
    }

    // Queue demonstration
    val queue = myQueueOf<String>()
    println(queue.isEmpty())
    queue.enqueue("First")
    queue.enqueue("Second")
    println(queue.dequeue())
    println(queue.safeDequeue())
    try {
        println(queue.dequeue())
    } catch (e: Exception) {
        println(e.toString())
    }

    val newQueue = MyQueue<String>()
    newQueue.enqueue("Test")
    println(newQueue.dequeue())

    val newNewQueue = myQueueOf<String>("First", "Second", "Third")
    newNewQueue.enqueue("Fourth")
    println(newNewQueue.dequeue())
    println(newNewQueue.dequeue())

    newNewQueue.iterator().withIndex().forEach {
        println("Item ${it.value} with index ${it.index}")
    }
}