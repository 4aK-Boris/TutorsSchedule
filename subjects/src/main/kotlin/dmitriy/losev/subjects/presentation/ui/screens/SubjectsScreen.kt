package dmitriy.losev.subjects.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dmitriy.losev.subjects.R
import dmitriy.losev.subjects.core.EMPTY_STRING
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.presentation.ui.views.SubjectCard
import dmitriy.losev.subjects.presentation.viewmodels.SubjectsScreenViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Loader
import dmitriy.losev.ui.views.Title
import dmitriy.losev.ui.views.buttons.FloatingAddIconButton
import dmitriy.losev.ui.views.popups.SubjectPopUp
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun SubjectsScreen(subjectsNavigationListener: SubjectsNavigationListener, viewModel: SubjectsScreenViewModel = koinNavViewModel()) {

    val scrollState = rememberLazyListState()

    val subjects by viewModel.subjects.collectAsState()
    val subjectPopUpVisible by viewModel.subjectPopUpVisible.collectAsState()
    val subject by viewModel.subject.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    LaunchedEffect(key1 = Unit) {
        viewModel.loadSubjects()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Title(title = stringResource(id = R.string.subjects_screen_title)) {
                viewModel.navigateToProfileScreen(subjectsNavigationListener)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingAddIconButton {
                viewModel.navigateToAddSubjectScreen(subjectsNavigationListener)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = backgroundColor),
            state = scrollState,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            itemsIndexed(
                items = subjects,
                key = { _, subject -> subject.id ?: EMPTY_STRING }
            ) { index, subject ->
                SubjectCard(index = index + 1, subject = subject) {
                    viewModel.openSubjectPopUp(subject)
                }
            }
        }

        if (isLoading) {
            Loader()
        }
    }

    SubjectPopUp(
        visible = subjectPopUpVisible,
        subjectName = subject?.name ?: EMPTY_STRING,
        onEditClicked = { viewModel.navigateToEditSubjectScreen(subjectsNavigationListener) },
        onDeleteClicked = viewModel::deleteSubject,
        close = viewModel::closeSubjectPopUp
    )
}