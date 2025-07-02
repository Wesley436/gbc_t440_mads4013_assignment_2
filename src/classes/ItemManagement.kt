package classes

interface ItemManagement<T: HasIdAndName> {
    var itemList: MutableList<T>
    var itemName: String

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

    fun inputItemInfo(): T

    fun addItem(item: T) {
        itemList.add(item)
        println("$itemName added: ${item.name}")
    }

    fun addItem() {
        val item = inputItemInfo()
        addItem(item)
    }

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

    fun findItemByInput(): T {
        var targetItem: T? = null

        var id = ""
        var idFound = false
        while(!idFound) {
            print("Enter $itemName ID: ")
            id = readln()

            val pair = findItemById(id)
            idFound = pair.first
            targetItem = pair.second
        }

        return targetItem!!
    }

    fun validateUpdate(targetItem: T, updatedItem: T): Pair<Boolean, String> {
        return Pair(true, "")
    }

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

    fun validateDelete(itemId: String): Pair<Boolean, String> {
        return Pair(true, "")
    }

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