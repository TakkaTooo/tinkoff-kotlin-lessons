
fun main(){
    val testMage = Mage("Waldemar", 10)
    val testFighter = Fighter("Ludwig", 7, "Ahr!!!")
    val testThief = Thief("Marcus", 5,HealthPoints = 80, Agility = 14)
    val heroes = listOf<Hero>(testMage, testFighter, testThief)

    // Demonstration of general functionality
    heroes.forEach{
        it.sayGreeting()
        it.useSkill()
        it.printHp()
        println("-----------------------")
    }

    // Demonstration of unique functionality
    heroes.forEach{
        when (it){
            is Fighter -> it.cryOut()
            is Mage -> it.switchSpell()
            is Thief -> it.hide()
        }
        println("-----------------------")
    }
    testThief.printHp()
    testMage.regen(testThief)
    testThief.printHp()

}
