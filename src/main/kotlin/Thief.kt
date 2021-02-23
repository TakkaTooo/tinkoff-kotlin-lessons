class Thief(
    override val name: String,
    override var lvl: Int,
    override var healthPoints: Int = 100,
    private var agility: Int = 10
) : Hero {
    override val type: String = "Thief"
    override fun useSkill() {
        if (agility > 25){
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