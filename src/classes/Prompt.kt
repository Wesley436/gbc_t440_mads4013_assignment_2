package classes

abstract class Prompt {
    var isContinue = true

    open fun printPrompt() {

    }

    open fun processUserInput(userInput: String) {
        this.isContinue = false
    }

    fun runPrompt() {
        while(isContinue) {
            printPrompt()
            val userInput = readln()
            processUserInput(userInput)
        }

        this.isContinue = true
    }
}