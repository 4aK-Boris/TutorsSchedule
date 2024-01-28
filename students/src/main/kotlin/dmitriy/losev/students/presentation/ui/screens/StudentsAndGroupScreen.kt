package dmitriy.losev.students.presentation.ui.screens

import androidx.compose.runtime.Composable
import dmitriy.losev.core.models.SimpleStudent
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.viewmodels.students.StudentsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StudentsAndGroupsScreen(
    studentsNavigationListener: StudentsNavigationListener,
    openDrawer: () -> Unit,
    viewModel: StudentsScreenViewModel = koinViewModel()
) {

//    val screenState by viewModel.screenState.collectAsState(initial = StudentsScreenState.NEW)
//
//    val navigateToStudentScreen: (SimpleStudent) -> Unit = { student ->
//        viewModel.navigateToStudentScreen(studentsNavigationListener, student)
//    }
//
//    val navigateToUpdateStudentScreen: (SimpleStudent) -> Unit = { student ->
//        viewModel.navigateToUpdateStudentScreen(studentsNavigationListener, student)
//    }
//
//    val newStudents by viewModel.newStudents.collectAsState(initial = emptyList())
//    val activeStudents by viewModel.activeStudents.collectAsState(initial = emptyList())
//    val archiveStudents by viewModel.archiveStudents.collectAsState(initial = emptyList())
//
//    val isLoading by viewModel.isLoading.collectAsState()
//    val isAuth by viewModel.isAuth.collectAsState()
//
//    LaunchedEffect(key1 = Unit) {
//        viewModel.checkAuth()
//        viewModel.loadScreenState()
//        viewModel.loadStudents()
//    }
//
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .tutorsScheduleBackground(),
//        topBar = {
//            StudentsTopBar(screenState, openDrawer)
//        },
//        floatingActionButtonPosition = FabPosition.End,
//        floatingActionButton = {
//            if (isAuth) {
//                StudentsFloatingButton(
//                    icon = Icons.Default.Add,
//                    contentDescription = stringResource(id = R.string.add_student),
//                    buttonColor = dmitriy.losev.ui.theme.TutorsScheduleTheme.colors.floatingButton,
//                    tint = dmitriy.losev.ui.theme.colors.White
//                ) {
//                    viewModel.navigateToAddStudentScreen(studentsNavigationListener)
//                }
//            }
//        },
//        bottomBar = {
//            if (isAuth) {
//                StudentsBottomBar(screenState, viewModel::onScreenStateChanged)
//            }
//        }
//    ) { paddingValues ->
//        if (isAuth) {
//            LoadingBox(isLoading = isLoading, modifier = Modifier.padding(paddingValues = paddingValues)) {
//                when (screenState) {
//                    StudentsScreenState.NEW -> StudentsScreenBody(
//                        students = newStudents,
//                        navigateToStudentScreen = navigateToStudentScreen,
//                        navigateToUpdateStudentScreen = navigateToUpdateStudentScreen
//                    )
//
//                    StudentsScreenState.ACTIVE -> StudentsScreenBody(
//                        students = activeStudents,
//                        navigateToStudentScreen = navigateToStudentScreen,
//                        navigateToUpdateStudentScreen = navigateToUpdateStudentScreen
//                    )
//
//                    StudentsScreenState.ARCHIVE -> StudentsScreenBody(
//                        students = archiveStudents,
//                        navigateToStudentScreen = navigateToStudentScreen,
//                        navigateToUpdateStudentScreen = navigateToUpdateStudentScreen
//                    )
//                }
//            }
//        } else {
//            Box(
//                modifier = Modifier
//                    .padding(paddingValues = paddingValues)
//                    .fillMaxSize(), contentAlignment = Alignment.Center
//            ) {
//                DefaultButton(text = stringResource(id = R.string.enter)) {
//                    viewModel.auth(studentsNavigationListener)
//                }
//            }
//        }
//    }
}

@Composable
private fun StudentsScreenBody(
    students: List<SimpleStudent>,
    navigateToStudentScreen: (SimpleStudent) -> Unit,
    navigateToUpdateStudentScreen: (SimpleStudent) -> Unit
) {

//    val scrollState = rememberLazyListState()
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .tutorsScheduleBackground(),
//        state = scrollState,
//        contentPadding = PaddingValues(vertical = 32.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        itemsIndexed(items = students) { index, student ->
//            StudentItem(
//                index = index + 1,
//                name = student.name,
//                navigateToStudentScreen = { navigateToStudentScreen(student) },
//                navigateToUpdateStudentScreen = { navigateToUpdateStudentScreen(student) }
//            )
//        }
//    }
}
