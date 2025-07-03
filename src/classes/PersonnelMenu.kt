package classes

class PersonnelMenu(val personnelService: PersonnelService): Menu() {
    init {
        this.title = "=== Personnel Management ==="
        this.options = mutableListOf(
            "Add Personnel",
            "Update Personnel",
            "Delete Personnel",
            "List Personnels"
        )
    }

    /**
     *  Processes user's input and starts flows for managing personnel items
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
            1 -> personnelService.addItem()
            2 -> personnelService.updateItem()
            3 -> personnelService.deleteItem()
            4 -> personnelService.listItems()
            0 -> this.isContinue = false
            else -> {
                println("Invalid option")
                println()
            }
        }
    }
}