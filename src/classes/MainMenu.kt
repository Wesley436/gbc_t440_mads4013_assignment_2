package classes

class MainMenu(val shipMenu: ShipMenu, val personnelMainMenu: PersonnelMenu, val assignmentOperationsMenu: AssignmentOperationsMenu, val shipService: ShipService, val personnelService: PersonnelService) : Menu() {
    init {
        this.title = "=== UNSEC Personnel Resourcing Command ==="
        this.options = mutableListOf(
            "Ship Management",
            "Personnel Management",
            "Assignment Operations",
            "Generate Reports",
            "Display All Data"
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
            1 -> shipMenu.runPrompt()
            2 -> personnelMainMenu.runPrompt()
            3 -> assignmentOperationsMenu.runPrompt()
            4 -> generateReports()
            5 -> displayAllData()
            0 -> this.isContinue = false
            else -> {
                println("Invalid option")
                println()
            }
        }
    }

    fun generateReports() {
        val skillPersonnelMap = HashMap<String, MutableList<Personnel>>()
        for (personnel in personnelService.itemList.filter { it -> it.assignedShipId != null }) {
            for (skill in personnel.skillSets) {
                val personnelList = skillPersonnelMap.get(skill)
                if (personnelList != null) {
                    personnelList.add(personnel)
                } else {
                    skillPersonnelMap.put(skill, mutableListOf(personnel))
                }
            }
        }

        println()
        for ((key, value) in skillPersonnelMap) {
            println("Personnel with skill $key")
            for (personnel in value) {
                println("Personnel ID: ${personnel.id}, Name: ${personnel.name}, Rank: ${personnel.rank}, Assigned ship ID: ${personnel.assignedShipId}")
            }
            println()
        }
    }

    fun displayAllData() {
        shipService.listItems()
        personnelService.listItems()
    }
}