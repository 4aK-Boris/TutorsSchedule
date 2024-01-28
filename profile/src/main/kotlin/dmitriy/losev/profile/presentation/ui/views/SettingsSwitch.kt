package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.views.DefaultSwitch
import dmitriy.losev.ui.views.SubBodyText
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer

@Composable
internal fun SettingsSwitch(state: Boolean, onStateChanged: (Boolean) -> Unit, title: String, hint: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

            Title2Text(text = title)

            VerticalSpacer(height = 7.dp)

            SubBodyText(text = hint)
        }

        DefaultSwitch(state = state, onStateChanged = onStateChanged)
    }
}