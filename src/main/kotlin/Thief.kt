class Thief(
    override val Name: String,
    override var Lvl: Int,
    override var HealthPoints: Int = 100
) : Hero {
    override val Type: String = "Thief"

    override fun useSkill() {
        "Tried to steal".goAction()
    }

    fun hide(){
         "Hide away".goAction()
    }

    override fun printHp() {
        println("Thief hp: ")
        super.printHp()
    }
}