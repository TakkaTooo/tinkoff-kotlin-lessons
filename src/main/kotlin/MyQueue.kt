import java.lang.Exception
/**
 * A class that implements a queue data structure based on MutableList
 * @constructor constructs a new empty queue
 */
class MyQueue<T>() : MyCollection<T>() {

    /**
     * Adds an item to the end of the queue
     * @param element adding to queue item
     */
    fun enqueue(element: T) {
        list.add(element)
    }

    /**
     * Returns the item from the beginning of the queue, if the queue is empty it returns null
     *
     */
    fun safeDequeue(): T? = list.removeFirstOrNull()

    /**
     * Returns the item from the beginning of the queue
     * @throws Exception in case if queue is empty
     */
    fun dequeue(): T = if (isNotEmpty()) list.removeFirst() else throw Exception("Queue is empty")

}

/**
 * Returns a new queue of given items
 * @param elements items for adding to queue
 */
fun <T> myQueueOf(vararg elements: T): MyQueue<T> {
    val queue = MyQueue<T>()
    elements.forEach {
        queue.enqueue(it)
    }
    return queue
}