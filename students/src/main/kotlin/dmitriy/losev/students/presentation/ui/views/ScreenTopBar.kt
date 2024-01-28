package dmitriy.losev.students.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dmitriy.losev.students.core.StudentsAndGroupsState
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.FilterText
import dmitriy.losev.ui.views.HorizontalSpacer
import dmitriy.losev.ui.views.textfields.FilterTextField

@Composable
fun ScreenTopBar(
    filterText: String,
    onFilterTextChanged: (String) -> Unit,
    state: StudentsAndGroupsState,
    onStudentsAndGroupsStateChanged: (StudentsAndGroupsState) -> Unit
) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundTitle

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .background(color = backgroundColor)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilterTextField(text = filterText, onTextChanged = onFilterTextChanged, modifier = Modifier.width(width = screenWidth - 216.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {

            TopBarButton(
                title = StudentsAndGroupsState.ACTIVE.title,
                isChecked = StudentsAndGroupsState.ACTIVE == state
            ) {
                onStudentsAndGroupsStateChanged(StudentsAndGroupsState.ACTIVE)
            }

            HorizontalSpacer(width = 10.dp)

            TopBarButton(
                title = StudentsAndGroupsState.ARCHIVE.title,
                isChecked = StudentsAndGroupsState.ARCHIVE == state
            ) {
                onStudentsAndGroupsStateChanged(StudentsAndGroupsState.ARCHIVE)
            }
        }
    }
}

@Composable
private fun TopBarButton(title: String, isChecked: Boolean, onClick: () -> Unit) {

    val colors = LocalTutorsScheduleColors.current

    val checkedBackgroundColor = colors.filterPrimary
    val notCheckedBackgroundColor = colors.filterSecondary

    val checkedTextColor = colors.iconPrimary
    val notCheckedTextColor = colors.iconSecondary

    val backgroundColor = if (isChecked) checkedBackgroundColor else notCheckedBackgroundColor
    val textColor = if (isChecked) checkedTextColor else notCheckedTextColor

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(size = 7.dp))
            .clickable(onClick = onClick, role = Role.Button)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 7.dp))
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        FilterText(text = title, color = textColor)
    }
}