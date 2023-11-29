package dmitriy.losev.tutorsschedule.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmitriy.losev.core.presentation.ui.views.TopBar

@Composable
fun CalendarScreen(openDrawer: () -> Unit) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBar(
            titleText = "Календарь",
            leftIcon = Icons.Default.Menu,
            leftContentDescription = "",
            onLeftIconClick = openDrawer
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CalendarScreen")
        }
    }
}