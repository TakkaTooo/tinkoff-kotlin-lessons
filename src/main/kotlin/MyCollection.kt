/**
 * Class for any collections based on a MutableList
 */
abstract class MyCollection<T> {

    /**
     * Main list with collection elements
     *
     */
    protected val list: MutableList<T?> = mutableListOf()

    /**
     * Returns the size of the collection
     *
     */
    var size: Int = 0
        protected set

    /**
     * Property storing the index of the first element in the collection
     *
     */
    protected var headIndex: Int = 0

    init {
        list.add(null)
    }

    /**
     * Removes all elements from the collection
     *
     */
    fun clear() = list.clear()

    /**
     * Returns true if collection is empty
     *
     */
    fun isEmpty(): Boolean = size == 0

    /**
     * Returns true if collection is not empty
     *
     */
    fun isNotEmpty(): Boolean = !isEmpty()

}