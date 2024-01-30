package dmitriy.losev.students.presentation.ui.screens.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.views.StudentChooserView
import dmitriy.losev.students.presentation.viewmodels.groups.ChooseStudentsScreenViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.TitleWithSaveIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChooseStudentsScreen(
    studentsNavigationListener: StudentsNavigationListener,
    groupId: String?,
    viewModel: ChooseStudentsScreenViewModel = koinViewModel()
) {

    val students by viewModel.students.collectAsState(initial = emptyList())

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadData(groupId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleWithSaveIcon(
                title = stringResource(id = R.string.choose_students_screen_title),
                back = { viewModel.back(studentsNavigationListener) },
                save = { viewModel.navigateGroupScreen(studentsNavigationListener, groupId) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = backgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {

            itemsIndexed(
                items = students,
                key = { _, student -> student.first.id to student.second }
            ) { position, student ->

                StudentChooserView(student = student.first, isChoose = student.second, position = position + 1) {
                    viewModel.onSelectedStudentIdsChanged(studentId = student.first.id)
                }
            }
        }
    }
}

