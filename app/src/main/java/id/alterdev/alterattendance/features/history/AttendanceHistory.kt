package id.alterdev.alterattendance.features.history

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import kotlin.random.Random

data class AttendanceHistory(
    val date: String,
    val day: String,
    val checkInTime: String,
    val checkOutTime: String
)


// Outside the function
val dateFormat = SimpleDateFormat("dd/MM/yyyy")
val dayFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
val random = Random.Default

fun createDummyAttendanceHistory(): List<AttendanceHistory> {
    val dummyList = mutableListOf<AttendanceHistory>()
    val checkInTimes = listOf(
        "08:00", "08:15", "08:30", "08:45", "09:00"
    )
    val checkOutTimes = listOf(
        "17:00", "17:15", "17:30", "17:45", "18:00"
    )

    repeat(17) {
        val randomDay = random.nextInt(1, 32)
        val dateStr = String.format("%02d", randomDay)
        val date = dateFormat.parse("$dateStr/01/2024")
        val day = dayFormat.format(date)
        val history = AttendanceHistory(
            date = dateStr,
            day = day,
            checkInTime = checkInTimes[random.nextInt(checkInTimes.size)],
            checkOutTime = checkOutTimes[random.nextInt(checkOutTimes.size)]
        )
        dummyList.add(history)
    }
    return dummyList
}