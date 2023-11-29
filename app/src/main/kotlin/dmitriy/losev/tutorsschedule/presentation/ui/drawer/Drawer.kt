package dmitriy.losev.tutorsschedule.presentation.ui.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitriy.losev.tutorsschedule.presentation.viewmodels.MainScreenViewModel

@Composable
fun Drawer(
    viewModel: MainScreenViewModel,
    navController: NavController,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(modifier = Modifier.width(width = 200.dp)) {
        DrawerScreens.entries.forEach { screen ->
            DrawerItem(screen = screen) {
                closeDrawer()
                viewModel.changeScreen(screen, navController)
            }
        }
    }
}

@Composable
private fun DrawerItem(screen: DrawerScreens, onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .height(height = 48.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = screen.image, contentDescription = screen.title)
            Text(text = screen.title)
        }
    }
}