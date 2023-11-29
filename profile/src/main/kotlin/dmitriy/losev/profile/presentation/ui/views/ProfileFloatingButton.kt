package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.profile.R

@Composable
fun ProfileFloatingButton(buttonColor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier.size(size = 48.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp,
                pressedElevation = 8.dp
            )
        ) {
            IconButton(
                modifier = Modifier
                    .size(size = 48.dp)
                    .background(color = buttonColor, shape = CircleShape),
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.save_changes),
                    modifier = Modifier.size(size = 36.dp)
                )
            }
        }
    }
}