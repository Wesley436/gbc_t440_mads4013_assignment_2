package classes

class ShipService(val personnelService: PersonnelService): ItemManagement<Ship> {
    override var itemList = mutableListOf<Ship>()
    override var itemName = "Ship"

    fun findNumberOfCrewAssignedToShip(shipId: String): Int {
        var crewCount = 0
        for (personnel in personnelService.itemList) {
            if (personnel.assignedShipId == shipId) {
                crewCount++
            }
        }

        return crewCount
    }

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

    override fun validateUpdate(targetItem: Ship, updatedItem: Ship): Pair<Boolean, String> {
        val crewCount = findNumberOfCrewAssignedToShip(targetItem.id)

        if (crewCount >= updatedItem.maxCrew) {
            return Pair(false, "Number of personnel currently assigned is more than the max crew inputted")
        }

        return Pair(true, "")
    }

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

    override fun validateDelete(itemId: String): Pair<Boolean, String> {
        for (personnel in personnelService.itemList) {
            if (personnel.assignedShipId == itemId) {
                return Pair(false, "Please unassign all personnel from the ship")
            }
        }

        return Pair(true, "")
    }
}