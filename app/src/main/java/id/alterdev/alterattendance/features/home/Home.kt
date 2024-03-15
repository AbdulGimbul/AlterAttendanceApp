package id.alterdev.alterattendance.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.alterdev.alterattendance.R
import id.alterdev.alterattendance.ui.components.AlterAttendancePreview
import id.alterdev.alterattendance.ui.components.CheckInOutCard
import id.alterdev.alterattendance.ui.components.CheckInOutType
import id.alterdev.alterattendance.ui.components.OverviewCard
import id.alterdev.alterattendance.ui.components.OverviewType

@Composable
fun HomeScreen() {

}

@Composable
fun Home() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner",
            modifier = Modifier.height(230.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomCenter
        )
        Column(
            modifier = Modifier
                .padding(top = 190.dp)
                .clip(
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Hai Budi", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                        Text(text = "Senin 13 Maret, 2024")
                    }
                    Image(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account",
                        modifier = Modifier.size(50.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CheckInOutCard(modifier = Modifier.weight(1f), type = CheckInOutType.CheckInType)
                    CheckInOutCard(modifier = Modifier.weight(1f), type = CheckInOutType.CheckOutType)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Overview", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OverviewCard(modifier = Modifier.weight(1f), type = OverviewType.Sick)
                    OverviewCard(modifier = Modifier.weight(1f), type = OverviewType.Permit)
                    OverviewCard(modifier = Modifier.weight(1f), type = OverviewType.Leeave)
                }
            }
        }
    }
}


@Composable
@AlterAttendancePreview
fun HomePreview() {
    Home()
}