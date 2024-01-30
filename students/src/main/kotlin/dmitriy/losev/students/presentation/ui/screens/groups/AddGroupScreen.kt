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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.views.AddButton
import dmitriy.losev.students.presentation.ui.views.StudentsView
import dmitriy.losev.students.presentation.viewmodels.groups.AddGroupScreenViewModel
import dmitriy.losev.ui.views.TitleWithSaveIcon
import dmitriy.losev.ui.views.VerticalSpacer
import dmitriy.losev.ui.views.boxes.ColumnSecondaryWithLoader
import dmitriy.losev.ui.views.textfields.DefaultTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddGroupScreen(
    studentsNavigationListener: StudentsNavigationListener,
    viewModel: AddGroupScreenViewModel = koinViewModel()
) {

    val name by viewModel.name.collectAsState()
    val students by viewModel.students.collectAsState()

    val nameTextFieldState by viewModel.nameTextFieldState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadData()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.add_group_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                save = { viewModel.saveChanges(studentsNavigationListener) })
        }
    ) { paddingValues ->

        ColumnSecondaryWithLoader(modifier = Modifier.padding(paddingValues = paddingValues), viewModel = viewModel) {

            VerticalSpacer(height = 24.dp)

            DefaultTextField(
                title = stringResource(id = R.string.name_title),
                hint = stringResource(id = R.string.name_title),
                text = name,
                onTextChanged = viewModel::onNameChanged,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true,
                textFieldState = nameTextFieldState,
                clearTextFieldState = viewModel::clearNameTextFieldState
            )

            VerticalSpacer(height = 24.dp)

            AddButton(title = stringResource(id = R.string.add_students)) {
                viewModel.navigateToChooseStudentsScreen(studentsNavigationListener)
            }

            VerticalSpacer(height = 24.dp)

            StudentsView(
                students = students,
                onEditClick = { viewModel.navigateToChooseStudentsScreen(studentsNavigationListener) }
            )
        }
    }
}