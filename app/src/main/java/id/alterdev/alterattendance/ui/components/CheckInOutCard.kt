package id.alterdev.alterattendance.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.alterdev.alterattendance.R
import id.alterdev.alterattendance.ui.theme.bg_checkin_color
import id.alterdev.alterattendance.ui.theme.bg_checkout_color
import id.alterdev.alterattendance.ui.theme.button_color
import id.alterdev.alterattendance.ui.theme.disabled_button_color

@Composable
fun CheckInOutCard(
    modifier: Modifier = Modifier,
    type: CheckInOutType,
    isEnable: Boolean = true,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getBgColorForInOut(type)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = getIconForInOut(type)),
                    contentDescription = "Masuk",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(10.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = getTitleForInOut(type), style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "--:-- WIB",
                style = MaterialTheme.typography.bodyLarge,
            )
            Button(
                onClick = {
                          onClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = isEnable,
                colors = ButtonColors(
                    containerColor = button_color,
                    contentColor = Color.White,
                    disabledContainerColor = disabled_button_color,
                    disabledContentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.CenterFocusWeak,
                    contentDescription = "Check In Icon",
                    modifier = Modifier.padding(end = 8.dp),
                )
                Text(text = getTitleForInOut(type))
            }
        }
    }
}

fun getIconForInOut(type: CheckInOutType): Int {
    return when (type) {
        CheckInOutType.CheckInType -> R.drawable.checkin_img
        CheckInOutType.CheckOutType -> R.drawable.checkout_img // Replace with checkout image drawable id
    }
}

fun getTitleForInOut(type: CheckInOutType): String {
    return when (type) {
        CheckInOutType.CheckInType -> "Masuk"
        CheckInOutType.CheckOutType -> "Pulang" // Replace with checkout image drawable id
    }
}

fun getBgColorForInOut(type: CheckInOutType): Color {
    return when (type) {
        CheckInOutType.CheckInType -> bg_checkin_color
        CheckInOutType.CheckOutType -> bg_checkout_color // Replace with checkout color
    }
}

sealed class CheckInOutType {
    data object CheckInType : CheckInOutType()
    data object CheckOutType : CheckInOutType()
}


@AlterAttendancePreview
@Composable
fun CheckInOutCardPreview() {
    CheckInOutCard(type = CheckInOutType.CheckInType, onClick = {})
}