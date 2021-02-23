interface Hero {

    val name: String
    var lvl: Int
    val type: String
    var healthPoints: Int

    fun String.goAction(){
        println("$type $name $lvl LVL do:\n $this")
    }

    fun printHp(){
        println("Current HP: $healthPoints")
    }

    fun sayGreeting() = "Hi, i am ${type.toLowerCase()} $name, my LVL is $lvl".goAction()

    fun useSkill()
}