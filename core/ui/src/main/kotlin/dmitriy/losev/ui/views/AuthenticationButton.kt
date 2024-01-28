package dmitriy.losev.ui.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationButton(modifier: Modifier, icon: Painter, contentDescription: String, onClick: () -> Unit) {
    IconButton(modifier = modifier.size(size = 40.dp), onClick = onClick) {
        Icon(painter = icon, contentDescription = contentDescription, modifier = Modifier.size(size = 40.dp))
    }
}