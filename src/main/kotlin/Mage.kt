enum class Spell(val manaCost: Int, val caption: String) {
    Fire(10, "Fire"), Wind(15, "Wind")
}

class Mage(
    override val name: String,
    override var lvl: Int,
    override var healthPoints: Int = 100
) : Hero {
    override val type: String = "Mage"
    private var manaPoints: Int = 100
    private var currentSpell: Spell = Spell.Fire
        private set
    override fun useSkill() {
        castSpell()
        "Attack with ${currentSpell.caption}".goAction()
        println("Current mana points: $manaPoints")
    }

    private fun castSpell(){
        this.manaPoints -= currentSpell.manaCost
    }

    fun regen(){
        "Regen...".goAction()
        this.manaPoints += 10
        println("Current mana points: $manaPoints")
    }

    fun regen(h: Hero){
        ("Regen HP for ${h.type.toLowerCase()} ${h.name}").goAction()
        h.healthPoints = if (h.healthPoints < 100)  h.healthPoints + 10 else 100
    }

     fun switchSpell(){
         currentSpell = if (currentSpell == Spell.Fire){
             Spell.Wind
         } else {
             Spell.Fire
         }
         "Spell changed to $currentSpell".goAction()
    }
}