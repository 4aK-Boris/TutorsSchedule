package dmitriy.losev.students.presentation.ui.screens.groups

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.views.AddButton
import dmitriy.losev.students.presentation.ui.views.StudentMenuItem
import dmitriy.losev.students.presentation.ui.views.StudentsView
import dmitriy.losev.students.presentation.viewmodels.groups.GroupScreenViewModel
import dmitriy.losev.ui.views.TitleWithEditIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.textfields.DefaultTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupScreen(
    studentsNavigationListener: StudentsNavigationListener,
    groupId: String,
    viewModel: GroupScreenViewModel = koinViewModel()
) {

    val name by viewModel.name.collectAsState()
    val students by viewModel.students.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadData(groupId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithEditIcon(
                title = stringResource(id = R.string.group_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                edit = { viewModel.navigateToUpdateGroupScreen(studentsNavigationListener, groupId) })
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.name_title),
                hint = stringResource(id = R.string.name_title),
                text = name,
                enabled = false
            )

            VerticalSpacer(height = 24.dp)

            AddButton(title = stringResource(id = R.string.add_students)) {
                viewModel.navigateToChooseStudentsScreen(studentsNavigationListener, groupId)
            }

            VerticalSpacer(height = 24.dp)

            StudentsView(
                students = students,
                onEditClick = { viewModel.navigateToChooseStudentsScreen(studentsNavigationListener, groupId) },
                onClick = { viewModel.navigateToStudentsAndGroupsScreen(studentsNavigationListener, groupId) }
            )

            VerticalSpacer(height = 24.dp)

            StudentMenuItem(title = stringResource(id = R.string.upcoming_lessons_title), onClick = { }, onMenuClick = { })

            VerticalSpacer(height = 40.dp)
        }
    }
}