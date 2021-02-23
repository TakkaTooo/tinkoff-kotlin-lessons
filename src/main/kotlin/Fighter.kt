
class Fighter(
    override val name: String,
    override var lvl: Int,
    private var cry: String,
    override var healthPoints: Int = 100
) : Hero {
    override val type = "Fighter"
    private var weapon: String? = getWeapon()

    override fun useSkill() = "Attack with ${this.weapon ?: "Arms"}".goAction()

    fun cryOut(){
        cry.goAction()
    }

    private fun getWeapon() = when {
        lvl > 20 -> "Rapier"
        lvl > 10 -> "Axe"
        lvl > 5 -> "Sword"
        else -> null
    }

}