import java.lang.Exception
/**
 * A class that implements a queue data structure based on MutableList
 * @constructor constructs a new empty queue
 */
class MyQueue<T> : MyCollection<T>() {

    /**
     * Property storing the index of the last element in the queue
     *
     */
    private var tailIndex: Int = 0

    /**
     * Adds an item to the end of the queue
     * @param element adding to queue item
     *
     */
    fun enqueue(element: T) {
        if (isNotEmpty()) {
            tailIndex++
        }
        list.add(tailIndex, element)

        size++
    }

    /**
     * Returns the item from the beginning of the queue, if the queue is empty it returns null
     *
     */
    fun safeDequeue(): T? {
        if (isEmpty()) {
            return null
        }
        val element = list[headIndex]
        list[headIndex] = null
        if (headIndex != tailIndex) {
            headIndex++
        }
        size--
        return element
    }

    /**
     * Returns the item from the beginning of the queue
     * @throws Exception in case if queue is empty
     *
     */
    fun dequeue(): T = if (isEmpty()) throw Exception("Queue is empty") else safeDequeue()!!

}

/**
 * Returns a new queue of given items
 * @param elements items for adding to queue
 *
 */
fun <T> myQueueOf(vararg elements: T): MyQueue<T> {
    val queue = MyQueue<T>()
    elements.forEach {
        queue.enqueue(it)
    }
    return queue
}