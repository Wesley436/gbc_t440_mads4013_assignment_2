package classes

abstract class Prompt {
    var isContinue = true

    /**
     *  Prints the prompt, to be overridden
     */
    open fun printPrompt() {

    }

    /**
     *  Logic for processing the user's input, and exits the prompt by setting isContinue to false depending on the user's response
     */
    open fun processUserInput(userInput: String) {
        this.isContinue = false
    }

    /**
     *  The main cycle of printing the instruction for the user to input, processes the user's input, and then repeats once idContinue is set to false
     */
    fun runPrompt() {
        while(isContinue) {
            printPrompt()
            val userInput = readln()
            processUserInput(userInput)
        }

        this.isContinue = true
    }
}