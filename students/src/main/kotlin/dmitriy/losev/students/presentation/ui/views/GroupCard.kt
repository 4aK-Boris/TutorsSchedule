package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.theme.colors.Red
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.StudentsAndGroupText
import dmitriy.losev.ui.views.buttons.MoreIconButton

@Composable
fun GroupCard(name: String, isNew: Boolean = false, isArchive: Boolean = false, openPopUp: () -> Unit, onClick: () -> Unit) {

    val colors = LocalTutorsScheduleColors.current

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val textPrimaryColor = colors.textPrimary
    val textHintColor = colors.textHint

    val textColor = if (isArchive) textHintColor else textPrimaryColor

    Box(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick, role = Role.Button),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {

                StudentsAndGroupText(
                    text = name,
                    color = textColor,
                    modifier = Modifier
                        .widthIn(min = 1.dp, max = screenWidth - 128.dp)
                        .wrapContentWidth(align = Alignment.Start, unbounded = false)
                )

                if (isNew) {

                    HorizontalSpacer(width = 4.dp)

                    Box(
                        modifier = Modifier
                            .size(size = 6.dp)
                            .background(color = Red, shape = CircleShape)
                            .align(alignment = Alignment.Top)
                    )
                }
            }

            MoreIconButton(onClick = openPopUp)
        }
    }
}