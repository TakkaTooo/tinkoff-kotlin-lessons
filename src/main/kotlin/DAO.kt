interface DAO {
    fun getById(id: Int): Display?
    fun getAll(): List<Display>
}