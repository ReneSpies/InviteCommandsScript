import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

/**
 *    Created on: 6 Mar 2021
 *    For Project: KrautswarmRoster
 *    Author: René Jörg Spies
 *    Copyright: © 2021 ARES ID
 */


class ExcelWriter private constructor(private val list: List<String>) {

    fun write() {

        println("Writing to excel file...")

        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("commands")

        list.forEachIndexed { index, member ->

            val row = sheet.createRow(index)

            row.createCell(0).setCellValue("/invite $member")

        }

        sheet.autoSizeColumn(0)

        val fileOutputStream = FileOutputStream("./src/main/resources/commands.xlsx")
        workbook.write(fileOutputStream)
        fileOutputStream.close()
        workbook.close()

        println("Wrote to excel file.")

    }

    companion object {

        fun from(list: List<String>): ExcelWriter {

            println("Preparing to write to excel file...")

            return ExcelWriter(list)

        }

    }

}