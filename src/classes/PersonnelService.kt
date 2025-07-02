package classes

class PersonnelService: ItemManagement<Personnel> {
    override var itemList = mutableListOf<Personnel>()
    override var itemName = "Personnel"

    override fun inputItemInfo(): Personnel {
        var id = ""
        var inputValid = false
        while(!inputValid) {
            print("Enter Personnel ID: ")
            id = readln()

            inputValid = true
            if (id.isEmpty()) {
                println("Please enter a value")
                println()
                inputValid = false
            } else {
                for (personnel in itemList) {
                    if (personnel.id == id) {
                        println("Duplicate ID")
                        println()
                        inputValid = false
                        break
                    }
                }
            }
        }

        val name = nonNullStringInput("Enter Personnel Name: ")
        val rank = nonNullStringInput("Enter Rank: ")

        val skillSets = mutableListOf<String>()

        var isInputtingSkillSets = true
        while(isInputtingSkillSets) {
            println("Personnel Skillets: $skillSets")
            print("Enter a skill (leave empty to confirm all): ")
            var skill = readln()
            skill = skill.trim()
            if (skill.isEmpty()) {
                isInputtingSkillSets = false
            } else {
                skillSets.add(skill)
            }
        }

        return Personnel(id, name, rank, skillSets, null)
    }

    fun assignToShip(personnel: Personnel, ship: Ship) {
        personnel.assignedShipId = ship.id
        println("${personnel.name} assigned to ${ship.name}")
    }
}