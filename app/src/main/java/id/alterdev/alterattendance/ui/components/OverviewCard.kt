package id.alterdev.alterattendance.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.AssignmentLate
import androidx.compose.material.icons.filled.AssignmentReturned
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Sick
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.alterdev.alterattendance.ui.theme.bg_checkin_color
import id.alterdev.alterattendance.ui.theme.button_color
import id.alterdev.alterattendance.ui.theme.overview_bg_icon

@Composable
fun OverviewCard(modifier: Modifier = Modifier, type: OverviewType) {
    Card(
        modifier = modifier,
        colors = cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = cardElevation(
            2.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
        ) {
            Box(
                Modifier
                    .padding(bottom = 10.dp)
                    .clip(CircleShape)
                    .background(overview_bg_icon)
                    .align(Alignment.End),
            ) {
                Icon(
                    imageVector = getIconForOverview(type),
                    contentDescription = "null",
                    modifier = Modifier.padding(6.dp).size(32.dp),
                    tint = button_color
                )
            }
            Text(text = "15", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            Text(text = getTitleForOverview(type), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun getIconForOverview(type: OverviewType): ImageVector {
    return when (type) {
        is OverviewType.Sick -> Icons.Filled.Sick
        is OverviewType.Permit -> Icons.Filled.AssignmentReturned
        is OverviewType.Leeave -> Icons.Filled.AssignmentLate
    }
}

fun getTitleForOverview(type: OverviewType): String {
    return when (type) {
        is OverviewType.Sick -> "Sakit"
        is OverviewType.Permit -> "Izin"
        is OverviewType.Leeave -> "Alpha"
    }
}

sealed class OverviewType {
    data object Sick : OverviewType()
    data object Permit : OverviewType()
    data object Leeave : OverviewType()
}

@AlterAttendancePreview
@Composable
fun OverviewCardPreview(){
    OverviewCard(type = OverviewType.Sick)
}