enum class Spell(val manaCost: Int, val caption: String) {
    Fire(10, "Fire"), Wind(15, "Wind")
}

class Mage(
    override val Name: String,
    override var Lvl: Int,
    override var HealthPoints: Int = 100
) : Hero {
    override val Type: String = "Mage"
    private var ManaPoints: Int = 100
    private var CurrentSpell: Spell = Spell.Fire
        private set
    override fun useSkill() {
        castSpell()
        "Attack with ${CurrentSpell.caption}".goAction()
        println("Current mana points: $ManaPoints")
    }

    private fun castSpell(){
        this.ManaPoints -= CurrentSpell.manaCost
    }

    fun regen(){
        "Regen...".goAction()
        this.ManaPoints += 10
        println("Current mana points: $ManaPoints")
    }

    fun regen(h: Hero){
        ("Regen HP for ${h.Type.toLowerCase()} ${h.Name}").goAction()
        h.HealthPoints = if (h.HealthPoints < 100)  h.HealthPoints + 10 else 100
    }

     fun switchSpell(){
         CurrentSpell = if (CurrentSpell == Spell.Fire){
             Spell.Wind
         } else {
             Spell.Fire
         }
         "Spell changed to $CurrentSpell".goAction()
    }
}