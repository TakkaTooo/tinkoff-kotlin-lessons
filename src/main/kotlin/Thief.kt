class Thief(
    override val Name: String,
    override var Lvl: Int,
    override var HealthPoints: Int = 100,
    private val Agility: Int = 10
) : Hero {
    override val Type: String = "Thief"
    override fun useSkill() {
        if (Agility > 25){
            "Tried to steal...Successfully".goAction()
        } else {
            "Tried to steal...Unsuccessfully".goAction()
        }

    }

    fun hide(){
         "Hide away".goAction()
    }

    override fun printHp() {
        println("Thief hp: ")
        super.printHp()
    }
}