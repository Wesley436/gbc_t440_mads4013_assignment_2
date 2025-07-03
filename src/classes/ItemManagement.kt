package classes

interface ItemManagement<T: HasIdAndName> {
    var itemList: MutableList<T>
    var itemName: String

    /**
     *  Flow for getting a non-empty user input
     *  @param {String} prompt - The text to be printed to tell the user what to input
     *  @return {String} input - The user's input
     */
    fun nonNullStringInput(prompt: String): String {
        var inputValid = false
        var input = ""
        while(!inputValid) {
            print(prompt)
            input = readln()

            inputValid = true
            if (input.isEmpty()) {
                println("Please enter a value")
                println()
                inputValid = false
            }
        }

        return input
    }

    /**
     *  Flow for creating a T object via user inputs
     *  @return {T} - The T object created
     */
    fun inputItemInfo(): T

    /**
     *  Adding an item to the itemList
     *  @param {T} item - The T object added to itemList
     */
    fun addItem(item: T) {
        itemList.add(item)
        println("$itemName added: ${item.name}")
    }

    /**
     *  Adding an item to the itemList via creating the object with user inputs
     */
    fun addItem() {
        val item = inputItemInfo()
        addItem(item)
    }

    /**
     *  Finds an item from the itemList via checking their Id field
     *  @param {String} id - The id to be searched for
     *  @return {Pair<Boolean, T?>} - A pair of values for the search result, first is a Boolean indicating whether the item is found, second is the object if found, or null if it's not found
     */
    fun findItemById(id: String): Pair<Boolean, T?> {
        for (item in itemList) {
            if (item.id == id) {
                return Pair(true, item)
            }
        }

        println("ID not found")
        println()
        return Pair(false, null)
    }

    /**
     *  Finds an item from the itemList via user inputting the item's Id
     *  @return {T} targetItem - The object found
     */
    fun findItemByInput(): T {
        var targetItem: T? = null

        var itemFound = false
        while(!itemFound) {
            val id = nonNullStringInput("Enter $itemName ID: ")

            val pair = findItemById(id)
            itemFound = pair.first
            targetItem = pair.second
        }

        return targetItem!!
    }

    /**
     *  Validates if the inputted values for the updated item is valid or not
     *  @param {T} targetItem - The original item before updating
     *  @param {T} updatedItem - The updated item
     *  @return {Pair<Boolean, String>} - A pair of values for the validation result, first is a Boolean indicating whether the update is valid, second is reason if it's not valid
     *  Defaults returning true, override with custom validations
     */
    fun validateUpdate(targetItem: T, updatedItem: T): Pair<Boolean, String> {
        return Pair(true, "")
    }

    /**
     *  Updates an item
     *  Ends early if itemList is empty
     *  Finds the item from itemList via inputting its Id
     *  Input new values for the item
     *  Checks whether the update is valid
     *  If valid, updates the item by removing the original one and adding the updated one into itemList
     *  @return {Pair<T?, T?>} - A pair of values for the update result, first is the original item, second is the updated item, return nulls if update failed
     */
    fun updateItem(): Pair<T?, T?> {
        if (itemList.isEmpty()) {
            println("Empty $itemName list")
            println()
            return Pair(null, null)
        }

        val targetItem = findItemByInput()
        val updatedItem = inputItemInfo()

        val validationResult = validateUpdate(targetItem, updatedItem)
        if (validationResult.first) {
            itemList.remove(targetItem)
            itemList.add(updatedItem)

            println("Updated $itemName with ID: ${updatedItem.id}")
            println()

            return Pair(targetItem, updatedItem)
        } else {
            println("Cannot update $itemName with ID: ${targetItem.id}")
            println("Reason: ${validationResult.second}")
            println()

            return Pair(null, null)
        }
    }

    /**
     *  Validates if the item selected can be deleted or not
     *  @param {String} itemId - Id of the item selected to be deleted
     *  @return {Pair<Boolean, String>} - A pair of values for the validation result, first is a Boolean indicating whether the deletion is valid, second is reason if it's not valid
     *  Defaults returning true, override with custom validations
     */
    fun validateDelete(itemId: String): Pair<Boolean, String> {
        return Pair(true, "")
    }

    /**
     *  Deletes an item
     *  Ends early if itemList is empty
     *  Finds the item from itemList via inputting its Id
     *  Checks whether the deletion is valid
     *  If valid, deletes the item from itemList if valid
     */
    fun deleteItem() {
        if (itemList.isEmpty()) {
            println("Empty $itemName list")
            println()
            return
        }

        val targetItem = findItemByInput()
        val validationResult = validateDelete(targetItem.id)
        if (validationResult.first) {
            itemList.remove(targetItem)

            println("Deleted $itemName with ID: ${targetItem.id}")
            println()
        } else {
            println("Cannot delete $itemName with ID: ${targetItem.id}")
            println("Reason: ${validationResult.second}")
            println()
        }
    }

    /**
     *  List all items in itemList
     *  Ends early if itemList is empty
     *  Prints all item via their toString() function
     */
    fun listItems() {
        if (itemList.isEmpty()) {
            println("Empty $itemName list")
            println()
            return
        }

        for (item in itemList) {
            println()
            println(item.toString())
        }
        println()
    }
}