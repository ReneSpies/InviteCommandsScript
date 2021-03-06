import java.io.File

/**
 *    Created on: 6 Mar 2021
 *    For Project: KrautswarmRoster
 *    Author: René Jörg Spies
 *    Copyright: © 2021 ARES ID
 */


class Formatter private constructor(private val filePath: String) {

    /**
     * Converts the .txt-file into a List<String>.
     * Then, formats each member of the list so that it can be written into the excel file.
     *
     * @return A list containing each member's Goonfleet Dot Com addresses.
     */
    fun getFormattedList(): List<String> {

        println("Starting to format list...")

        val list = getList() // Convert file content to list
        val listWithoutDates = getListWithoutDates(list) // Remove the dates from each element of the list
        val listWithFormattedNames =
            getListWithFormattedNames(listWithoutDates) // Format the names of each element of the list
        val listWithoutSnowflakes =
            getListWithoutSnowflakes(listWithFormattedNames) // Remove all known snowflakes from each element of the list

        println("List formatted.")

        return listWithoutSnowflakes

    }

    /**
     * Takes a list and removes all elements that are known to be snowflakes from it.
     *
     * @param listWithFormattedNames Needs an already formatted list so the snowflakes can be identified.
     *
     * @return A list without the known snowflakes.
     */
    private fun getListWithoutSnowflakes(listWithFormattedNames: List<String>): List<String> {

        println("Removing snowflakes...")

        val temporaryList =
            listWithFormattedNames.toMutableList() // Create a temporary mutable list from the given list
        val knownSnowflakes = listOf(
            "kilgarth@goonfleet.com",
            "merkelchen@goonfleet.com",
            "sarin_blackfist@goonfleet.com"
        ) // Create a list carrying the addresses of all known snowflakes

        // Iterate over the known snowflakes list and check for matches in the given list and remove those
        knownSnowflakes.forEach { snowflake ->

            // Remove all matches
            temporaryList.remove(
                // Find matches
                temporaryList.find {

                    val trimmedLine = it.trim() // Trim the line to avoid whitespace errors

                    snowflake == trimmedLine // Check for a match

                }
            )

        }

        return temporaryList

    }

    /**
     * Takes a list and formats each element to have this pattern:
     * first_last@goonfleet.com.
     *
     * @param listWithoutDates Needs an already formatted list so the pattern can be applied correctly.
     *
     * @return Same list with each element formatted in above pattern.
     */
    private fun getListWithFormattedNames(listWithoutDates: List<String>): List<String> {

        println("Formatting names...")

        val temporaryList = listWithoutDates.toMutableList() // Create a temporary mutable list from the given list

        temporaryList.replaceAll { it.toLowerCase() } // Format each element to be lower case
        temporaryList.replaceAll { it.replace(" ", "_") } // Replace all whitespace from each element with an underscore
        temporaryList.replaceAll {
            it.replace(
                it,
                "$it@goonfleet.com"
            )
        } // Append the Goonfleet Dot Com domain on each element

        return temporaryList

    }

    /**
     * Takes a list and removes the dates from each element that are in this pattern:
     * yyyy MMM dd HH:mm:ss.
     *
     * @param list Needs the converted content from the .txt-file.
     *
     * @return Same list without the dates on each element.
     */
    private fun getListWithoutDates(list: List<String>): List<String> {

        println("Removing dates...")

        val temporaryList = list.toMutableList() // Create a temporary mutable list from the given list

        // Remove the date from each element and trim the line
        temporaryList.replaceAll { it.replace("\\d{4}.{3}[A-Za-z].\\d{2}.\\d{2}:\\d{2}:\\d{2}".toRegex(), "").trim() }

        return temporaryList

    }

    /**
     * Converts the given .txt-file into a List<String>.
     *
     * @return List<String> converted from the .txt-file.
     */
    private fun getList(): List<String> {

        println("Converting .txt-file...")

        val temporaryList = mutableListOf<String>() // Make a temporary mutable list
        val bufferedReader = File(filePath).bufferedReader() // Get the buffered reader from the file from its path

        // Read each line from the buffered reader, trim it and put it into the list
        bufferedReader.forEachLine { line ->

            temporaryList.add(line.trim())

        }

        bufferedReader.close() // Close the buffered reader for garbage collection

        return temporaryList

    }

    companion object {

        /**
         * Creates a [Formatter] instance from the given .txt-file.
         *
         * @return [Formatter] instance.
         */
        fun from(filePath: String): Formatter {

            println("Preparing to format list...")

            return Formatter(filePath)

        }

    }

}