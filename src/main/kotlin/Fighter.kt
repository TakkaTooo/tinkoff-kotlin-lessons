
class Fighter(
    override val Name: String,
    override var Lvl: Int,
    private var Cry: String,
    override var HealthPoints: Int = 100
) : Hero {
    override val Type = "Fighter"
    private var weapon: String? = getWeapon()

    override fun useSkill() = "Attack with ${this.weapon ?: "Arms"}".goAction()

    fun cryOut(){
        Cry.goAction()
    }

    private fun getWeapon() = when {
        Lvl > 20 -> "Rapier"
        Lvl > 10 -> "Axe"
        Lvl > 5 -> "Sword"
        else -> null
    }

}