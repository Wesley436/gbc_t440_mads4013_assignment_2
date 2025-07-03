package classes

data class Personnel(override var id: String, override var name: String, var rank: String, var skillSets: MutableList<String>, var assignedShipId: String?): HasIdAndName {
    /**
     *  Formats the Ship object's data to be printed out
     */
    override fun toString(): String {
        var skillSetsString = "None"
        if (skillSets.isNotEmpty()) {
            skillSetsString = skillSets[0]
            for ((i, skill) in skillSets.iterator().withIndex()) {
                if (i > 0) {
                    skillSetsString += ", $skill"
                }
            }
        }

        return "Personnel ID: $id, Name: $name, Rank: $rank, Assigned ship ID: ${if (assignedShipId != null) assignedShipId else "Unassigned"}\nSkillsets: $skillSetsString"
    }
}