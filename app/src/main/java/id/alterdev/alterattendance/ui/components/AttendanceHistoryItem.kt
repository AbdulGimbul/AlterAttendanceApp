package id.alterdev.alterattendance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.alterdev.alterattendance.features.history.AttendanceHistory
import id.alterdev.alterattendance.ui.theme.md_theme_light_primaryContainer
import id.alterdev.alterattendance.ui.theme.md_theme_light_secondaryContainer

@Composable
fun AttendanceHistoryItem(modifier: Modifier = Modifier, attendance: AttendanceHistory) {
    Card(onClick = { /*TODO*/ }, modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(md_theme_light_secondaryContainer)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding()
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
                    .padding(8.dp)
                    .weight(0.6f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = attendance.date,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    )
                    Text(text = attendance.day)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Masuk Kelas",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = attendance.checkInTime)
            }
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(Modifier.height(48.dp), color = Color.Black)
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Keluar Kelas",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = attendance.checkOutTime)
            }
        }
    }
}

@AlterAttendancePreview
@Composable
private fun StudentAttendanceListItemPreview() {
    AttendanceHistoryItem(
        attendance = AttendanceHistory(
            date = "13",
            day = "Senin",
            checkInTime = "07:00 WIB",
            checkOutTime = "14:00 WIB"
        )
    )
}