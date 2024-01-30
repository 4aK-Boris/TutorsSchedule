package dmitriy.losev.students.presentation.ui.screens.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.students.core.StudentsAndGroupsState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.views.AnimatedBodyHorizontal
import dmitriy.losev.students.presentation.ui.views.GroupCard
import dmitriy.losev.students.presentation.ui.views.ScreenTopBar
import dmitriy.losev.students.presentation.viewmodels.groups.GroupsScreenViewModel
import dmitriy.losev.ui.theme.LocalTutorsScheduleColors
import dmitriy.losev.ui.views.Loader
import dmitriy.losev.ui.views.buttons.FloatingAddIconButton
import dmitriy.losev.ui.views.popups.ArchiveGroupPopUp
import dmitriy.losev.ui.views.popups.DeleteGroupPopUp
import dmitriy.losev.ui.views.popups.MenuGroupPopUp

@Composable
fun GroupsScreen(studentsNavigationListener: StudentsNavigationListener, viewModel: GroupsScreenViewModel) {

    val studentsAndGroupsState by viewModel.studentsAndGroupsState.collectAsState()
    val activeGroups by viewModel.activeGroups.collectAsState(initial = emptyList())
    val archiveGroups by viewModel.archiveGroups.collectAsState(initial = emptyList())

    val menuGroupPopUpVisible by viewModel.menuGroupPopUpVisible.collectAsState()
    val deletePopUpVisible by viewModel.deletePopUpVisible.collectAsState()
    val archivePopUpVisible by viewModel.archivePopUpVisible.collectAsState()

    val group by viewModel.group.collectAsState()

    val filterText by viewModel.filterText.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

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
                    viewModel.navigateToAddGroupScreen(studentsNavigationListener)
                }
            }
        }
    ) { paddingValues ->

        AnimatedBodyHorizontal(
            modifier = Modifier.padding(paddingValues = paddingValues),
            state = studentsAndGroupsState == StudentsAndGroupsState.ACTIVE
        ) { state ->

            if (state) {

                GroupsLazyColumn(groups = activeGroups) { group ->
                    GroupCard(
                        name = group.name,
                        isNew = group.groupType == GroupType.NEW,
                        openPopUp = { viewModel.openMenuGroupPopUp(group) },
                        onClick = { viewModel.navigateToGroupScreen(studentsNavigationListener, group.id) }
                    )
                }
            } else {

                GroupsLazyColumn(groups = archiveGroups) { group ->
                    GroupCard(
                        name = group.name,
                        isArchive = true,
                        openPopUp = { viewModel.openMenuGroupPopUp(group) },
                        onClick = { viewModel.navigateToGroupScreen(studentsNavigationListener, group.id) }
                    )
                }
            }
        }

        if (isLoading) {
            Loader()
        }
    }

    MenuGroupPopUp(
        visible = menuGroupPopUpVisible,
        isActive = group?.groupType != GroupType.ARCHIVE,
        groupName = group?.name,
        editProfile = { viewModel.navigateToEditGroupScreen(studentsNavigationListener) },
        moveToArchive = viewModel::openArchivePopUp,
        bringItBack = viewModel::bringItBackFromArchive,
        delete = viewModel::openDeletePopUp,
        close = viewModel::closeMenuGroupPopUp
    )

    DeleteGroupPopUp(
        visible = deletePopUpVisible,
        close = viewModel::closeDeletePopUp,
        delete = viewModel::deleteGroup
    )

    ArchiveGroupPopUp(
        visible = archivePopUpVisible,
        close = viewModel::closeArchivePopUp,
        move = viewModel::moveToArchive
    )
}

@Composable
private fun GroupsLazyColumn(groups: List<Group>, groupCard: @Composable (Group) -> Unit) {

    val backgroundColor = LocalTutorsScheduleColors.current.backgroundSecondary

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = groups) { group ->
            groupCard(group)
        }
    }
}