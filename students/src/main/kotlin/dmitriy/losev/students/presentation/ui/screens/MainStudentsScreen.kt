package dmitriy.losev.students.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dmitriy.losev.students.core.ScreenState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.screens.groups.GroupsScreen
import dmitriy.losev.students.presentation.ui.screens.students.StudentsScreen
import dmitriy.losev.students.presentation.ui.views.AnimatedBody
import dmitriy.losev.students.presentation.ui.views.StateTopBar
import dmitriy.losev.students.presentation.viewmodels.MainStudentsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.groups.GroupsScreenViewModel
import dmitriy.losev.students.presentation.viewmodels.students.StudentsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainStudentsScreen(
    studentsNavigationListener: StudentsNavigationListener,
    isStudents: Boolean,
    groupId: String?,
    studentId: String?,
    studentsScreenViewModel: StudentsScreenViewModel = koinViewModel(),
    groupsScreenViewModel: GroupsScreenViewModel = koinViewModel(),
    viewModel: MainStudentsScreenViewModel = koinViewModel()
) {

    val screenState by viewModel.screenState.collectAsState()

    Log.d("TAG", isStudents.toString())
    Log.d("TAG", groupId.toString())
    Log.d("TAG", studentId.toString())

    LaunchedEffect(key1 = Unit) {
        viewModel.setState(isStudents)
        studentsScreenViewModel.loadStudents(groupId)
        groupsScreenViewModel.loadData(studentId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            StateTopBar(screenState = screenState, onScreenStateChanged = viewModel::onScreenStateChanged)
        }
    ) { paddingValues ->

        AnimatedBody(
            modifier = Modifier.padding(paddingValues = paddingValues),
            state = screenState == ScreenState.STUDENTS
        ) { state ->
            if (state) {
                StudentsScreen(studentsNavigationListener, studentsScreenViewModel)
            } else {
                GroupsScreen(studentsNavigationListener, groupsScreenViewModel)
            }
        }
    }
}