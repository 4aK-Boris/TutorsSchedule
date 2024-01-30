package dmitriy.losev.students.presentation.ui.screens.students

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.students.core.StudentsAndGroupsState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.views.AnimatedBodyHorizontal
import dmitriy.losev.students.presentation.ui.views.ScreenTopBar
import dmitriy.losev.students.presentation.ui.views.StudentCard
import dmitriy.losev.students.presentation.viewmodels.students.StudentsScreenViewModel
import dmitriy.losev.ui.views.Loader
import dmitriy.losev.ui.views.buttons.FloatingAddIconButton
import dmitriy.losev.ui.views.popups.ArchiveStudentPopUp
import dmitriy.losev.ui.views.popups.CallPopUp
import dmitriy.losev.ui.views.popups.DeleteStudentPopUp
import dmitriy.losev.ui.views.popups.MenuStudentPopUp
import dmitriy.losev.ui.views.popups.MessagePopUp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StudentsScreen(studentsNavigationListener: StudentsNavigationListener, viewModel: StudentsScreenViewModel) {

    val studentsAndGroupsState by viewModel.studentsAndGroupsState.collectAsState()
    val activeStudents by viewModel.activeStudents.collectAsState(initial = emptyList())
    val archiveStudents by viewModel.archiveStudents.collectAsState(initial = emptyList())

    val menuStudentPopUpVisible by viewModel.menuStudentPopUpVisible.collectAsState()
    val callPopUpVisible by viewModel.callPopUpVisible.collectAsState()
    val messagePopUpVisible by viewModel.messagePopUpVisible.collectAsState()
    val deleteStudentPopUpVisible by viewModel.deleteStudentPopUpVisible.collectAsState()
    val archiveStudentPopUpVisible by viewModel.archiveStudentPopUpVisible.collectAsState()

    val student by viewModel.student.collectAsState()

    val filterText by viewModel.filterText.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            ScreenTopBar(
                filterText = filterText,
                onFilterTextChanged = viewModel::onFilterTextChanged,
                state = studentsAndGroupsState,
                onStudentsAndGroupsStateChanged = viewModel::onStudentsAndGroupsStateChanged
            )
        },
        floatingActionButton = {
            if (studentsAndGroupsState == StudentsAndGroupsState.ACTIVE) {
                FloatingAddIconButton {
                    viewModel.navigateToAddStudentScreen(studentsNavigationListener)
                }
            }
        }
    ) { paddingValues ->

        AnimatedBodyHorizontal(
            modifier = Modifier.padding(paddingValues = paddingValues),
            state = studentsAndGroupsState == StudentsAndGroupsState.ACTIVE
        ) { state ->

            if (state) {

                StudentsLazyColumn {
                    itemsIndexed(items = activeStudents) { index, student ->
                        StudentCard(name = student.name,
                            isNew = student.isNew,
                            position = index + 1,
                            openPopUp = { viewModel.openMenuStudentPopUp(studentId = student.id) },
                            onClick = { viewModel.navigateToStudentScreen(studentsNavigationListener, student.id) })
                    }
                }
            } else {

                StudentsLazyColumn {
                    itemsIndexed(items = archiveStudents) { index, student ->
                        StudentCard(name = student.name,
                            position = index + 1,
                            isArchive = true,
                            openPopUp = { viewModel.openMenuStudentPopUp(studentId = student.id) },
                            onClick = { viewModel.navigateToStudentScreen(studentsNavigationListener, student.id) })
                    }
                }
            }
        }

        if (isLoading) {
            Loader()
        }
    }

    MenuStudentPopUp(
        visible = menuStudentPopUpVisible,
        isActive = student?.studentType != StudentType.ARCHIVE,
        studentName = student?.shortName,
        editProfile = { viewModel.navigateToEditStudentScreen(studentsNavigationListener) },
        call = viewModel::openCallPopUp,
        write = viewModel::openMessagePopUp,
        moveToArchive = viewModel::openArchiveStudentPopUp,
        bringItBack = viewModel::bringItBackFromArchive,
        makePayment = { },
        delete = viewModel::openDeleteStudentPopUp,
        close = viewModel::closeMenuStudentPopUp
    )

    CallPopUp(
        visible = callPopUpVisible,
        studentName = student?.shortName,
        onPhoneClicked = viewModel::onPhoneCallClicked,
        onSkypeClicked = viewModel::onSkypeClicked,
        onDiscordClicked = viewModel::onDiscordClicked,
        onViberClicked = viewModel::onViberClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        close = viewModel::closeCallPopUp
    )

    MessagePopUp(
        visible = messagePopUpVisible,
        studentName = student?.shortName,
        onSmsClicked = viewModel::onSmsClicked,
        onTelegramClicked = viewModel::onTelegramClicked,
        onWhatsAppClicked = viewModel::onWhatsAppClicked,
        onViberClicked = viewModel::onViberClicked,
        onEmailClicked = viewModel::onEmailClicked,
        close = viewModel::closeMessagePopUp
    )

    DeleteStudentPopUp(
        visible = deleteStudentPopUpVisible,
        close = viewModel::closeDeleteStudentPopUp,
        delete = viewModel::deleteStudent
    )

    ArchiveStudentPopUp(
        visible = archiveStudentPopUpVisible,
        close = viewModel::closeArchiveStudentPopUp,
        move = viewModel::moveToArchive
    )
}

@Composable
private fun StudentsLazyColumn(items: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items()
    }
}