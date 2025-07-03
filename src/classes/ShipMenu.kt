package classes

class ShipMenu(val shipService: ShipService): Menu() {
    init {
        this.title = "=== Ship Management ==="
        this.options = mutableListOf(
        "Add Ship",
        "Update Ship",
        "Delete Ship",
        "List Ships"
        )
    }

    /**
     *  Processes user's input and starts flows for managing ship items
     *  @param {String} userInput - The user's input, expects a number between 0 and 4
     */
    override fun processUserInput(userInput: String) {
        val number = userInput.toIntOrNull()
        if (number == null) {
            println("Invalid option")
            println()
            return
        }

        println()
        when (number) {
            1 -> shipService.addItem()
            2 -> shipService.updateItem()
            3 -> shipService.deleteItem()
            4 -> shipService.listItems()
            0 -> this.isContinue = false
            else -> {
                println("Invalid option")
                println()
            }
        }
    }
}