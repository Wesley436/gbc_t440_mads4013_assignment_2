import classes.AssignmentOperationsMenu
import classes.MainMenu
import classes.Personnel
import classes.PersonnelMenu
import classes.PersonnelService
import classes.Ship
import classes.ShipMenu
import classes.ShipService

fun main() {
    val personnelService = PersonnelService()
    val shipService = ShipService(personnelService)

    shipService.addItem(Ship("1", "Enterprise", "Constellation", 430))
    shipService.addItem(Ship("2", "Defiant", "Constellation", 100))
    shipService.addItem(Ship("3", "Voyager", "Constellation", 250))

    personnelService.addItem(Personnel("1", "James T. Kirk", "Command", mutableListOf("Command", "Piloting"), null))
    personnelService.addItem(Personnel("2", "Spock", "Crew", mutableListOf("Piloting"), null))
    personnelService.addItem(Personnel("3", "Montgomery Scott", "Crew", mutableListOf("Engineering"), null))
    personnelService.addItem(Personnel("4", "Leonard McCoy", "Crew", mutableListOf("Engineering", "Maintenance"), null))
    personnelService.addItem(Personnel("5", "B'Elanna Torres", "Crew", mutableListOf("Maintenance", "Medical"), null))
    personnelService.addItem(Personnel("6", "The Doctor", "Crew", mutableListOf("Medical"), null))

    val personnelList = personnelService.itemList
    val shipList = shipService.itemList
    personnelService.assignToShip(personnelList[0], shipList[0])
    personnelService.assignToShip(personnelList[1], shipList[0])
    personnelService.assignToShip(personnelList[2], shipList[0])
    personnelService.assignToShip(personnelList[3], shipList[0])
    personnelService.assignToShip(personnelList[4], shipList[2])
    personnelService.assignToShip(personnelList[5], shipList[2])
    println("Welcome to UNSEC Personnel Resourcing Command System")
    println()

    val shipMenu = ShipMenu(shipService)
    val personnelMenu = PersonnelMenu(personnelService)
    val assignmentOperationsMenu = AssignmentOperationsMenu(shipService, personnelService)

    val mainMenu = MainMenu(shipMenu, personnelMenu, assignmentOperationsMenu, shipService, personnelService)
    mainMenu.runPrompt()
}