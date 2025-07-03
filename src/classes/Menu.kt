package classes

/**
 *  A type of prompt that prints a list of options to be selected by user
 */
abstract class Menu : Prompt() {
    var title: String = ""
    var options: MutableList<String> = mutableListOf()

    /**
     *  Prints all options for the user to select, and an exit option by inputting 0
     */
    override fun printPrompt() {
        println(title)
        for ((index, option) in options.iterator().withIndex()) {
            println("${index + 1}. $option")
        }

        println("0. Exit")
        print("Select option: ")
    }
}