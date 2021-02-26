import java.lang.Exception

/**
 * A class that implements a stack data structure based on MutableList
 * @constructor constructs a new empty stack
 */
class MyStack<T>() : MyCollection<T>() {

    /**
     * Adds an item to the top of the stack
     * @param element adding item
     *
     */
    fun push(element: T) = list.add(0, element)

    /**
     * Returns the item from the top of the stack, if the stack is empty it returns null
     *
     */
    fun safePop(): T? = list.removeFirstOrNull()

    /**
     * Returns the item from the top of the stack
     * @throws Exception in case if stack is empty
     *
     */
    fun pop(): T = if (isNotEmpty()) list.removeFirst() else throw Exception("Stack is empty")
}

/**
 * Returns a new stack of given items
 * @param elements items for adding to stack
 */
fun <T> myStackOf(vararg elements: T): MyStack<T> {
    val stack = MyStack<T>()
    elements.forEach {
        stack.push(it)
    }
    return stack
}

