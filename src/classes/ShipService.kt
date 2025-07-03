package classes

class ShipService(val personnelService: PersonnelService): ItemManagement<Ship> {
    override var itemList = mutableListOf<Ship>()
    override var itemName = "Ship"

    /**
     *  Get the number of personnel assigned to a ship
     *  @param {String} shipId - Id of the ship
     *  @return {Int} crewCount - Number of personnel assigned to the ship
     */
    fun findNumberOfCrewAssignedToShip(shipId: String): Int {
        var crewCount = 0
        for (personnel in personnelService.itemList) {
            if (personnel.assignedShipId == shipId) {
                crewCount++
            }
        }

        return crewCount
    }

    /**
     *  Creates a ship object via user inputs
     *  Inputs an unique Id
     *  Inputs a ship name
     *  Inputs a ship class
     *  Inputs a max crew size
     *  @return {Ship} - The ship object created
     */
    override fun inputItemInfo(): Ship {
        var id = ""
        var inputValid = false
        while(!inputValid) {
            print("Enter Ship ID: ")
            id = readln()

            inputValid = true
            if (id.isEmpty()) {
                println("Please enter a value")
                println()
                inputValid = false
            } else {
                for (ship in itemList) {
                    if (ship.id == id) {
                        println("Duplicate ID")
                        println()
                        inputValid = false
                        break
                    }
                }
            }
        }

        val name = nonNullStringInput("Enter Ship Name: ")
        val shipClass = nonNullStringInput("Enter Ship Class: ")

        var maxCrew: Int? = null
        inputValid = false
        while(!inputValid) {
            print("Enter Max Crew: ")
            val maxCrewInput = readln()
            maxCrew = maxCrewInput.toIntOrNull()
            if (maxCrew == null || maxCrew < 0) {
                println("Invalid number")
                continue
            }

            inputValid = true
        }

        return Ship(id, name, shipClass, maxCrew!!)
    }

    /**
     *  Validates if the updated ship's maxCrew is larger than the current number of personnel assigned to the ship
     *  @param {Ship} targetItem - The original ship before updating
     *  @param {Ship} updatedItem - The updated ship
     *  @return {Pair<Boolean, String>} - A pair of values for the validation result, first is a Boolean indicating whether the update is valid, second is reason if it's not valid
     */
    override fun validateUpdate(targetItem: Ship, updatedItem: Ship): Pair<Boolean, String> {
        val crewCount = findNumberOfCrewAssignedToShip(targetItem.id)

        if (crewCount >= updatedItem.maxCrew) {
            return Pair(false, "Number of personnel currently assigned is more than the max crew inputted")
        }

        return Pair(true, "")
    }

    /**
     *  Updates a ship
     *  Goes through the ItemManagement's updateItem() flow
     *  If the update's result is valid (via checking whether the returned pair values are null or not)
     *  If the Id of the ship is updated, updates the assignedShipId of all the personnel currently assigned to the ship to the updated Id
     *  @return {Pair<T?, T?>} - Returns the same values from ItemManagement's updateItem()
     */
    override fun updateItem(): Pair<Ship?, Ship?> {
        val pair = super.updateItem()
        val originalShip = pair.first
        val updatedShip = pair.second
        if (originalShip != null && updatedShip != null) {
            for (personnel in personnelService.itemList) {
                if (personnel.assignedShipId == originalShip.id) {
                    personnel.assignedShipId = updatedShip.id
                }
            }
        }
        return pair
    }

    /**
     *  Disallow deleting the ship if there are still personnel assigned to the ship
     *  @param {String} itemId - Id of the ship selected to be deleted
     *  @return {Pair<Boolean, String>} - A pair of values for the validation result, first is a Boolean indicating whether the deletion is valid, second is reason if it's not valid
     */
    override fun validateDelete(itemId: String): Pair<Boolean, String> {
        for (personnel in personnelService.itemList) {
            if (personnel.assignedShipId == itemId) {
                return Pair(false, "Please unassign all personnel from the ship")
            }
        }

        return Pair(true, "")
    }
}