/**
 *    Created on: 6 Mar 2021
 *    For Project: InviteCommandsScript
 *    Author: René Jörg Spies
 *    Copyright: © 2021 ARES ID
 */


fun main() {

    val fileFormatter = Formatter.from("./src/main/resources/krautswarm_roster.txt")
    val formattedList = fileFormatter.getFormattedList()
    val excelWriter = ExcelWriter.from(formattedList)

    excelWriter.write()

}