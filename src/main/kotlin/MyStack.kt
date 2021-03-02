import java.lang.Exception

/**
 * A class that implements a stack data structure based on MutableList
 * @constructor constructs a new empty stack
 */
class MyStack<T> : MyCollection<T>() {

    /**
     * Adds an item to the top of the stack
     * @param element adding item
     *
     */
    fun push(element: T) {
        if (isNotEmpty()) {
            headIndex++
        }
        if (headIndex > list.size - 1) {
            list.add(headIndex, element)
        } else {
            list[headIndex] = element
        }
        size++
    }

    /**
     * Returns the item from the top of the stack, if the stack is empty it returns null
     *
     */
    fun safePop(): T? {
        if (isEmpty()) {
            return null
        }
        val element = list[headIndex]
        list[headIndex] = null
        if (headIndex >  0) {
            headIndex -= 1
        }
        size--
        return element
    }

    /**
     * Returns the item from the top of the stack
     * @throws Exception in case if stack is empty
     *
     */
    fun pop(): T = if (isEmpty()) throw Exception("Stack is empty") else safePop()!!
}

/**
 * Returns a new stack of given items
 * @param elements items for adding to stack
 *
 */
fun <T> myStackOf(vararg elements: T): MyStack<T> {
    val stack = MyStack<T>()
    elements.forEach {
        stack.push(it)
    }
    return stack
}

