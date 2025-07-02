package classes

open class Menu : Prompt() {
    var title: String = ""
    var options: MutableList<String> = mutableListOf()

    override fun printPrompt() {
        println(title)
        for ((index, option) in options.iterator().withIndex()) {
            println("${index + 1}. $option")
        }

        println("0. Exit")
        print("Select option: ")
    }
}