interface Hero {

    val Name: String
    var Lvl: Int
    val Type: String
    var HealthPoints: Int

    fun String.goAction(){
        println("$Type $Name $Lvl LVL do:\n $this")
    }

    fun printHp(){
        println("Current HP: $HealthPoints")
    }

    fun sayGreeting() = "Hi, i am ${Type.toLowerCase()} $Name, my LVL is $Lvl".goAction()

    fun useSkill()
}