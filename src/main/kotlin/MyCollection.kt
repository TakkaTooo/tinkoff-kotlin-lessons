/**
 * Class for any collections based on a MutableList
 */
abstract class MyCollection<T> : Iterable<T> {

    /**
     * Main list with collection elements
     */
    protected val list: MutableList<T> = mutableListOf()

    /**
     * Returns the size of the collection
     */
    private val size: Int
        get() = this.list.size

    /**
     * Removes all elements from the collection
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

    /**
     * An iterator for sequential access to the elements of the collection
     */
    override fun iterator(): Iterator<T> {
        return list.iterator()
    }
}