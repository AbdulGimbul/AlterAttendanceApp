package id.alterdev.alterattendance.features.history

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.alterdev.alterattendance.ui.components.AlterAttendancePreview
import id.alterdev.alterattendance.ui.components.AttendanceHistoryItem

@Composable
fun AttendanceHistoryScreen() {
    AttendanceHistory()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceHistory() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Riwayat Absensi", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }, modifier = Modifier.shadow(4.dp))
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            contentPadding = innerPadding
        ) {
            items(createDummyAttendanceHistory()) { attendanceHistory ->
                AttendanceHistoryItem(
                    attendance = attendanceHistory,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@AlterAttendancePreview
@Composable
private fun AttendanceHistoryPreview() {
    AttendanceHistory()
}