package dmitriy.losev.students.presentation.viewmodels.students

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dmitriy.losev.core.models.Contact
import dmitriy.losev.core.models.Student
import dmitriy.losev.students.R
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.CONTACT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.ContactIsNotLoadingException
import dmitriy.losev.students.core.exception.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.EMPTY_FIRST_NAME_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.PHONE_NUMBER_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.STUDENT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.StudentIsNotLoadingException
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contact.DeleteContactUseCase
import dmitriy.losev.students.domain.usecases.contact.GetContactsUseCase
import dmitriy.losev.students.domain.usecases.contacts.ClearPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.contacts.PickContactUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentUseCase
import dmitriy.losev.students.domain.usecases.student.UpdateStudentUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UpdateStudentScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val pickContactUseCase: PickContactUseCase,
    private val clearPhoneNumberUseCase: ClearPhoneNumberUseCase,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val getStudentUseCase: GetStudentUseCase,
    private val getContactsUseCase: GetContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : BaseViewModel() {

    private var student: Student? = null

    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)
    private val _phoneNumber = MutableStateFlow(value = EMPTY_STRING)
    private val _email = MutableStateFlow(value = EMPTY_STRING)
    private val _skype = MutableStateFlow(value = EMPTY_STRING)
    private val _discord = MutableStateFlow(value = EMPTY_STRING)
    private val _comment = MutableStateFlow(value = EMPTY_STRING)
    private val _contacts = MutableStateFlow(value = emptyList<Contact>())

    private val _contact = MutableStateFlow<Contact?>(value = null)
    private val _editContactPopUpVisible = MutableStateFlow(value = false)

    private val _firstNameTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _emailTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _phoneNumberTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    private val _commentVisible = MutableStateFlow(value = false)

    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val patronymic = _patronymic.asStateFlow()
    val phoneNumber = _phoneNumber.asStateFlow()
    val email = _email.asStateFlow()
    val skype = _skype.asStateFlow()
    val discord = _discord.asStateFlow()
    val comment = _comment.asStateFlow()
    val contacts = _contacts.asStateFlow()

    val contact = _contact.asStateFlow()
    val editContactPopUpVisible = _editContactPopUpVisible.asStateFlow()

    val firstNameTextFieldState = _firstNameTextFieldState.asStateFlow()
    val emailTextFieldState = _emailTextFieldState.asStateFlow()
    val phoneNumberTextFieldState = _phoneNumberTextFieldState.asStateFlow()

    val commentVisible = _commentVisible.asStateFlow()

    fun onCommentVisibleChanged() {
        _commentVisible.value = _commentVisible.value.not()
    }

    fun clearFirstNameTextFieldState() {
        _firstNameTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearEmailTextFieldState() {
        _emailTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearPhoneNumberTextFieldState() {
        _phoneNumberTextFieldState.value = TextFieldState.DEFAULT
    }

    fun onFirstNameChanged(firstName: String) {
        _firstName.value = firstName
    }

    fun onLastNameChanged(lastName: String) {
        _lastName.value = lastName
    }

    fun onPatronymicChanged(patronymic: String) {
        _patronymic.value = patronymic
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    private fun onContactsChanged(contacts: List<Contact>) {
        _contacts.value = contacts
    }

    fun openEditContactPopUp(contact: Contact) {
        _contact.value = contact
        _editContactPopUpVisible.value = true
    }

    fun closeEditContactPopUp() {
        _editContactPopUpVisible.value = false
    }

    fun pickContactResult(result: ActivityResult) = runOnMain {
        if (result.resultCode == Activity.RESULT_OK) {
            safeNullableCall { pickContactUseCase.pickContact(intent = result.data) }.processing { phoneNumber ->
                if (!phoneNumber.isNullOrBlank()) {
                    safeCall { clearPhoneNumberUseCase.clearPhoneNumber(phoneNumber) }.processing { clearPhoneNumber ->
                        _phoneNumber.value = clearPhoneNumber
                    }
                }
            }
        }
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onSkypeChanged(skype: String) {
        _skype.value = skype
    }

    fun onDiscordChanged(discord: String) {
        _discord.value = discord
    }

    fun onCommentChanged(comment: String) {
        _comment.value = comment
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun saveChanges(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnIOWithLoading {
        checkStudentForNullableAndCopy { student ->
            safeCall { updateStudentUseCase.updateStudent(studentId, student) }.processingLoading {
                back(studentsNavigationListener)
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun pickContactIntent(permissionState: PermissionState, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = runOnIO {
        safeCall { pickContactUseCase.pickContactIntent(permissionState, launcher) }.processing()
    }

    fun navigateToAddContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        studentsNavigationUseCases.navigateToAddContactScreen(studentsNavigationListener, studentId)
    }

    fun navigateToContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) = runOnMain {
        closeEditContactPopUp()
        studentsNavigationUseCases.navigateToContactScreen(studentsNavigationListener, studentId, contactId)
    }

    fun navigateToEditContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        checkContactForNullable { contact ->
            closeEditContactPopUp()
            studentsNavigationUseCases.navigateToUpdateContactScreen(studentsNavigationListener, studentId, contact.id!!)
        }
    }

    fun deleteContact(studentId: String) = runOnIOWithLoading {
        checkContactForNullable { contact ->
            safeCall { deleteContactUseCase.deleteContact(studentId, contact.id!!) }.processingLoading {
                _contacts.value = _contacts.value.toMutableList().apply {
                    remove(contact)
                }
            }
        }
    }

    fun loadData(studentId: String) {
        loadStudent(studentId)
        loadContacts(studentId)
    }

    private fun loadStudent(studentId: String) = runOnIO {
        safeCall { getStudentUseCase.getStudent(studentId, ::onStudentFirebaseLoading, ::onStudentDatabaseLoading) }.processing()
    }

    private fun loadContacts(studentId: String) = runOnIO {
        safeCall { getContactsUseCase.getContacts(studentId, ::onContactsLoading) }
    }

    private fun onStudentFirebaseLoading(student: Student) {
        stopLoading()
        updateStudent(student = student)
    }

    private fun onStudentDatabaseLoading(student: Student?) {
        student?.let { onStudentFirebaseLoading(student) }
    }

    private fun onContactsLoading(contacts: List<Contact>) {
        if (_contacts.value != contacts) {
            onContactsChanged(contacts)
        }
    }

    private fun updateStudent(student: Student) {
        if (this.student != student) {
            onFirstNameChanged(firstName = student.firstName)
            onLastNameChanged(lastName = student.lastName)
            onPatronymicChanged(patronymic = student.patronymic)
            onPhoneNumberChanged(phoneNumber = student.phoneNumber)
            onEmailChanged(email = student.email)
            onSkypeChanged(skype = student.skype)
            onDiscordChanged(discord = student.discord)
            onCommentChanged(comment = student.comment)
            this.student = student
        }
    }

    private fun checkContactForNullable(f: suspend (Contact) -> Unit) = runOnIO {
        safeCall { _contact.value ?: throw ContactIsNotLoadingException() }.processing { contact ->
            f(contact)
        }
    }

    private fun checkStudentForNullableAndCopy(f: suspend (Student) -> Unit) = runOnIO {
        safeCall { student ?: throw StudentIsNotLoadingException() }.processing { student ->
            f(
                student.copy(
                    firstName = _firstName.value,
                    lastName = _lastName.value,
                    patronymic = _patronymic.value,
                    phoneNumber = _phoneNumber.value,
                    email = _email.value,
                    skype = _skype.value,
                    discord = _discord.value,
                    comment = _comment.value
                )
            )
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to R.string.empty_first_name_exception_message,
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to R.string.phone_number_validation_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            CONTACT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.contact_is_not_loading_exception_message,
            STUDENT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.student_is_not_loading_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to { _firstNameTextFieldState.value = TextFieldState.WARNING },
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to { _phoneNumberTextFieldState.value = TextFieldState.WARNING },
            EMAIL_VALIDATION_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.WARNING }
        )
}