package classes

data class Ship(override var id: String, override var name: String, var shipClass: String, var maxCrew: Int): HasIdAndName {
    /**
     *  Formats the Ship object's data to be printed out
     */
    override fun toString(): String {
        return "Ship ID: $id, Name: $name, Ship class: $shipClass, Max crew: $maxCrew"
    }
}