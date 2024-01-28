package dmitriy.losev.profile.presentation.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dmitriy.losev.profile.R
import dmitriy.losev.ui.theme.TutorsScheduleTheme
import dmitriy.losev.ui.views.SubTitleText
import dmitriy.losev.ui.views.Title2Text
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.buttons.IconButtonWithoutBackground

@Composable
internal fun BlockWithIcon(title: String, hint: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick, role = Role.Button),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

            Title2Text(text = title)

            VerticalSpacer(height = 6.dp)

            SubTitleText(text = hint)
        }

        IconButtonWithoutBackground(
            icon = painterResource(id = R.drawable.arrow),
            contentDescription = stringResource(id = R.string.more_detailed),
            shape = RectangleShape,
            size = 14.dp,
            onClick = onClick
        )
    }
}

@Composable
internal fun Block(title: String, hint: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Title2Text(text = title)

        VerticalSpacer(height = 6.dp)

        SubTitleText(text = hint)
    }
}


@Preview
@Composable
private fun TitlePreview() {

    TutorsScheduleTheme {
        BlockWithIcon(title = "Расписание занятий", hint = "Ваши ближайшие занятия") {

        }
    }
}