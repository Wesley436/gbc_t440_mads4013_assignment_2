package classes

class AssignmentOperationsMenu(val shipService: ShipService, val personnelService: PersonnelService): Menu() {
    init {
        this.title = "=== Assignment Operations ==="
        this.options = mutableListOf(
            "Assign Personnel"
        )
    }

    override fun processUserInput(userInput: String) {
        val number = userInput.toIntOrNull()
        if (number == null) {
            println("Invalid option")
            println()
            return
        }

        println()
        when (number) {
            1 -> assignPersonnel()
            0 -> this.isContinue = false
            else -> {
                println("Invalid option")
                println()
            }
        }
    }

    fun assignPersonnel() {
        if (personnelService.itemList.isEmpty()) {
            println("Empty Personnel list")
            println()
            return
        }

        if (shipService.itemList.isEmpty()) {
            println("Empty Ship list")
            println()
            return
        }

        val personnel = personnelService.findItemByInput()

        var ship: Ship? = null
        var isValidAssignment = false
        while(!isValidAssignment) {
            print("Enter ship ID to assign personnel (leave empty to unassign): ")
            val shipId = readln()

            if (shipId.isEmpty()) {
                personnel.assignedShipId = null
                return
            }

            val pair = shipService.findItemById(shipId)
            isValidAssignment = pair.first
            ship = pair.second

            if (ship != null) {
                val crewCount = shipService.findNumberOfCrewAssignedToShip(shipId)

                if (crewCount >= ship.maxCrew) {
                    println("Max number of the selected ship's crew reached")
                    println()
                    return
                }

                if (isValidAssignment) {
                    personnelService.assignToShip(personnel, ship)
                }
            }
        }
    }
}