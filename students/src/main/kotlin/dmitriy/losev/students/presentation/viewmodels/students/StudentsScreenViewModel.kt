package dmitriy.losev.students.presentation.viewmodels.students

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.Student
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsAndGroupsState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_DISCORD_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.EMPTY_PHONE_NUMBER_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.EMPTY_SKYPE_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.SKYPE_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.STUDENT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.StudentIsNotLoadingException
import dmitriy.losev.students.core.exception.TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.domain.models.ActiveStudent
import dmitriy.losev.students.domain.models.ArchiveStudent
import dmitriy.losev.students.domain.usecases.StudentsAuthUseCase
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contacts.CallPhoneUseCase
import dmitriy.losev.students.domain.usecases.contacts.DiscordUseCase
import dmitriy.losev.students.domain.usecases.contacts.EmailMessageUseCase
import dmitriy.losev.students.domain.usecases.contacts.SkypeUseCase
import dmitriy.losev.students.domain.usecases.contacts.SmsUseCase
import dmitriy.losev.students.domain.usecases.contacts.TelegramUseCase
import dmitriy.losev.students.domain.usecases.contacts.ViberAppUseCase
import dmitriy.losev.students.domain.usecases.contacts.WhatsAppUseCase
import dmitriy.losev.students.domain.usecases.student.ConvertStudentToActiveStudentUseCase
import dmitriy.losev.students.domain.usecases.student.ConvertStudentToArchiveStudentUseCase
import dmitriy.losev.students.domain.usecases.student.DeleteStudentUseCase
import dmitriy.losev.students.domain.usecases.student.GetFilterStudentsUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentsUseCase
import dmitriy.losev.students.domain.usecases.student.StudentBringBackFromArchiveUseCase
import dmitriy.losev.students.domain.usecases.student.StudentMoveToArchiveUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.menu.MenuStateHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

class StudentsScreenViewModel(
    private val menuStateHandler: MenuStateHandler,
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getStudentsUseCase: GetStudentsUseCase,
    private val studentsAuthUseCase: StudentsAuthUseCase,
    private val getStudentUseCase: GetStudentUseCase,
    private val studentMoveToArchiveUseCase: StudentMoveToArchiveUseCase,
    private val studentBringBackFromArchiveUseCase: StudentBringBackFromArchiveUseCase,
    private val convertStudentToActiveStudentUseCase: ConvertStudentToActiveStudentUseCase,
    private val convertStudentToArchiveStudentUseCase: ConvertStudentToArchiveStudentUseCase,
    private val callPhoneUseCase: CallPhoneUseCase,
    private val skypeUseCase: SkypeUseCase,
    private val discordUseCase: DiscordUseCase,
    private val viberAppUseCase: ViberAppUseCase,
    private val whatsAppUseCase: WhatsAppUseCase,
    private val telegramUseCase: TelegramUseCase,
    private val smsUseCase: SmsUseCase,
    private val emailMessageUseCase: EmailMessageUseCase,
    private val deleteStudentUseCase: DeleteStudentUseCase,
    private val getFilterStudentsUseCase: GetFilterStudentsUseCase
) : BaseViewModel() {

    private val _studentsAndGroupsState = MutableStateFlow(value = StudentsAndGroupsState.ACTIVE)

    private val _activeStudents = MutableStateFlow(value = emptyList<ActiveStudent>())
    private val _archiveStudents = MutableStateFlow(value = emptyList<ArchiveStudent>())

    private val _filterText = MutableStateFlow(value = EMPTY_STRING)

    private val _menuStudentPopUpVisible = MutableStateFlow(value = false)
    private val _callPopUpVisible = MutableStateFlow(value = false)
    private val _messagePopUpVisible = MutableStateFlow(value = false)
    private val _archiveStudentPopUpVisible = MutableStateFlow(value = false)
    private val _deleteStudentPopUpVisible = MutableStateFlow(value = false)
    private val _student = MutableStateFlow<Student?>(value = null)

    val studentsAndGroupsState = _studentsAndGroupsState.asStateFlow()

    val filterText = _filterText.asStateFlow()

    val activeStudents = combine(_activeStudents, _filterText) { students, text ->
        getFilterStudentsUseCase.getActiveFilterStudents(filterString = text, students = students)
    }

    val archiveStudents = combine(_archiveStudents, _filterText) { students, text ->
        getFilterStudentsUseCase.getArchiveFilterStudents(filterString = text, students = students)
    }

    val menuStudentPopUpVisible = _menuStudentPopUpVisible.asStateFlow()
    val callPopUpVisible = _callPopUpVisible.asStateFlow()
    val messagePopUpVisible = _messagePopUpVisible.asStateFlow()
    val archiveStudentPopUpVisible = _archiveStudentPopUpVisible.asStateFlow()
    val deleteStudentPopUpVisible = _deleteStudentPopUpVisible.asStateFlow()
    val student = _student.asStateFlow()

    fun onFilterTextChanged(filterText: String) {
        _filterText.value = filterText
    }

    fun onStudentsAndGroupsStateChanged(studentsAndGroupsState: StudentsAndGroupsState) {
        _studentsAndGroupsState.value = studentsAndGroupsState
    }

    fun navigateToAddStudentScreen(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.navigateToAddStudentScreen(studentsNavigationListener)
    }

    fun navigateToEditStudentScreen(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        checkStudentForNullable { student ->
            _menuStudentPopUpVisible.value = false
            studentsNavigationUseCases.navigateToUpdateStudentScreen(studentsNavigationListener, student.id)
        }
    }

    fun navigateToStudentScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        studentsNavigationUseCases.navigateToStudentScreen(studentsNavigationListener, studentId)
    }

    fun loadStudents(groupId: String?) = runOnIOWithLoading {
        println(groupId)
        safeCall { getStudentsUseCase.getStudents(groupId, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    fun openMenuStudentPopUp(studentId: String) = runOnIOWithLoading {
        safeCall { getStudentUseCase.getStudentFromFirebase(studentId) }.processingLoading { student ->
            _student.value = student
            menuStateHandler.closeMenu()
            _menuStudentPopUpVisible.value = true
        }
    }

    fun closeMenuStudentPopUp() {
        _menuStudentPopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openCallPopUp() {
        _menuStudentPopUpVisible.value = false
        _callPopUpVisible.value = true
    }

    fun closeCallPopUp() {
        _callPopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openMessagePopUp() {
        _menuStudentPopUpVisible.value = false
        _messagePopUpVisible.value = true
    }

    fun closeMessagePopUp() {
        _messagePopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openDeleteStudentPopUp() {
        _menuStudentPopUpVisible.value = false
        _deleteStudentPopUpVisible.value = true
    }

    fun closeDeleteStudentPopUp() {
        _deleteStudentPopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openArchiveStudentPopUp() {
        _menuStudentPopUpVisible.value = false
        _archiveStudentPopUpVisible.value = true
    }

    fun closeArchiveStudentPopUp() {
        _archiveStudentPopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun moveToArchive() = runOnIOWithLoading {
        closeArchiveStudentPopUp()
        checkStudentForNullable { student ->
            safeCall { studentMoveToArchiveUseCase.moveToArchive(studentId = student.id) }.processingLoading {
                removeStudentFromActiveStudents(studentId = student.id)
                addStudentToArchiveStudents(student)
            }
        }
    }

    fun deleteStudent() = runOnIOWithLoading {
        closeDeleteStudentPopUp()
        checkStudentForNullable { student ->
            safeCall { deleteStudentUseCase.deleteStudent(studentId = student.id) }.processingLoading {
                removeStudentFromArchiveStudents(studentId = student.id)
            }
        }
    }

    fun bringItBackFromArchive() = runOnIOWithLoading {
        closeMenuStudentPopUp()
        checkStudentForNullable { student ->
            safeCall { studentBringBackFromArchiveUseCase.bringItBackFromArchive(studentId = student.id) }.processingLoading {
                removeStudentFromArchiveStudents(studentId = student.id)
                addStudentToActiveStudents(student)
            }
        }
    }

    fun onPhoneCallClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { callPhoneUseCase.openApplication(phoneNumber = student.phoneNumber) }.processing()
            closeCallPopUp()
        }
    }

    fun onSkypeClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { skypeUseCase.openApplication(student.skype) }.processing()
            closeCallPopUp()
        }
    }

    fun onDiscordClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { discordUseCase.openApplication(student.discord) }.processing()
            closeCallPopUp()
        }
    }

    fun onViberClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { viberAppUseCase.openApplication(phoneNumber = student.phoneNumber) }.processing()
            closeCallPopUp()
            closeMessagePopUp()
        }
    }

    fun onWhatsAppClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { whatsAppUseCase.openApplication(phoneNumber = student.phoneNumber) }.processing()
            closeCallPopUp()
            closeMessagePopUp()
        }
    }

    fun onTelegramClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { telegramUseCase.openApplication(phoneNumber = student.phoneNumber) }.processing()
            closeCallPopUp()
            closeMessagePopUp()
        }
    }

    fun onSmsClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { smsUseCase.openApplication(phoneNumber = student.phoneNumber) }.processing()
            closeMessagePopUp()
        }
    }

    fun onEmailClicked() = runOnIO {
        checkStudentForNullable { student ->
            safeCall { emailMessageUseCase.openApplication(email = student.email) }.processing()
            closeMessagePopUp()
        }
    }

    private fun onFirebaseLoading(students: Pair<List<ActiveStudent>, List<ArchiveStudent>>) {
        stopLoading()
        updateActiveStudents(activeStudents = students.first)
        updateArchiveStudents(archiveStudents = students.second)
    }

    private fun onDatabaseLoading(students: Pair<List<ActiveStudent>, List<ArchiveStudent>>) {
        if (students.first.isNotEmpty() || students.second.isNotEmpty()) {
            onFirebaseLoading(students)
        }
    }

    private fun checkStudentForNullable(f: suspend (Student) -> Unit) = runOnIO {
        safeCall { _student.value ?: throw StudentIsNotLoadingException() }.processing { student ->
            f(student)
        }
    }

    private fun removeStudentFromActiveStudents(studentId: String) {
        _activeStudents.value = _activeStudents.value.toMutableList().apply {
            removeIf { student -> student.id == studentId }
        }
    }

    private fun removeStudentFromArchiveStudents(studentId: String) {
        _archiveStudents.value = _archiveStudents.value.toMutableList().apply {
            removeIf { student -> student.id == studentId }
        }
    }

    private fun addStudentToActiveStudents(student: Student) = runOnIO {
        _activeStudents.value = _activeStudents.value.toMutableList().apply {
            safeCall { convertStudentToActiveStudentUseCase.convertStudentToActiveStudent(student) }.processing { activeStudent ->
                add(activeStudent)
            }
        }
    }

    private fun addStudentToArchiveStudents(student: Student) = runOnIO {
        _archiveStudents.value = _archiveStudents.value.toMutableList().apply {
            safeCall { convertStudentToArchiveStudentUseCase.convertStudentToArchiveStudent(student) }.processing { archiveStudent ->
                add(archiveStudent)
            }
        }
    }

    private fun updateActiveStudents(activeStudents: List<ActiveStudent> ) {
        if (_activeStudents.value != activeStudents) {
            _activeStudents.value = activeStudents
        }
    }

    private fun updateArchiveStudents(archiveStudents: List<ArchiveStudent> ) {
        if (_archiveStudents.value != archiveStudents) {
            _archiveStudents.value = archiveStudents
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            STUDENT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.student_is_not_loading_exception_message,
            EMPTY_PHONE_NUMBER_EXCEPTION_CODE to R.string.empty_phone_number_exception_message,
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMPTY_SKYPE_EXCEPTION_CODE to R.string.empty_skype_exception_message,
            EMPTY_DISCORD_EXCEPTION_CODE to R.string.empty_discord_exception_message,
            WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.whats_app_application_is_not_installed_exception_message,
            SKYPE_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.skype_application_is_not_installed_exception_message,
            VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.viber_application_is_not_installed_exception_message,
            TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.telegram_application_is_not_installed_exception_message
        )
}