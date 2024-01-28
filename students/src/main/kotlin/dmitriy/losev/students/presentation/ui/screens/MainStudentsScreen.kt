package dmitriy.losev.students.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmitriy.losev.students.core.ScreenState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.screens.students.StudentsScreen
import dmitriy.losev.students.presentation.ui.views.StateTopBar
import dmitriy.losev.students.presentation.viewmodels.MainStudentsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.StudentsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainStudentsScreen(
    studentsNavigationListener: StudentsNavigationListener,
    studentsScreenViewModel: StudentsScreenViewModel = koinViewModel(),
    viewModel: MainStudentsScreenViewModel = koinViewModel()
) {

    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        studentsScreenViewModel.loadStudents()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            StateTopBar(screenState = screenState, onScreenStateChanged = viewModel::onScreenStateChanged)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (screenState == ScreenState.STUDENTS) {
                StudentsScreen(studentsNavigationListener, studentsScreenViewModel)
            } else {

            }
        }
    }
}