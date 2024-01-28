package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.profile.R
import dmitriy.losev.ui.views.buttons.PrimaryIconButton

@Composable
fun SettingsIconButton(onClick: () -> Unit) {

    PrimaryIconButton(
        icon = painterResource(id = R.drawable.settings),
        contentDescription = stringResource(id = R.string.settings),
        size = 32.dp,
        shape = RoundedCornerShape(size = 7.dp),
        onClick = onClick
    )
}