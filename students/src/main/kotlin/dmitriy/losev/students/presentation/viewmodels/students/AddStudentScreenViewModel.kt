package dmitriy.losev.students.presentation.viewmodels.students

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.EMPTY_FIRST_NAME_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.PHONE_NUMBER_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contacts.ClearPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.contacts.PickContactUseCase
import dmitriy.losev.students.domain.usecases.student.AddStudentUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AddStudentScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val pickContactUseCase: PickContactUseCase,
    private val clearPhoneNumberUseCase: ClearPhoneNumberUseCase,
    private val addStudentUseCase: AddStudentUseCase
) : BaseViewModel() {

    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)
    private val _phoneNumber = MutableStateFlow(value = EMPTY_STRING)
    private val _email = MutableStateFlow(value = EMPTY_STRING)
    private val _skype = MutableStateFlow(value = EMPTY_STRING)
    private val _discord = MutableStateFlow(value = EMPTY_STRING)
    private val _comment = MutableStateFlow(value = EMPTY_STRING)

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

    fun saveChanges(studentsNavigationListener: StudentsNavigationListener) = runOnIOWithLoading {
        safeCall {
            addStudentUseCase.addStudent(
                firstName = _firstName.value,
                lastName = _lastName.value,
                patronymic = _patronymic.value,
                phoneNumber = _phoneNumber.value,
                email = _email.value,
                skype = _skype.value,
                discord = _discord.value,
                comment = _comment.value
            )
        }.processingLoading { back(studentsNavigationListener) }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun pickContactIntent(permissionState: PermissionState, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = runOnIO {
        safeCall { pickContactUseCase.pickContactIntent(permissionState, launcher) }.processing()
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to R.string.empty_first_name_exception_message,
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to R.string.phone_number_validation_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to { _firstNameTextFieldState.value = TextFieldState.WARNING },
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to { _phoneNumberTextFieldState.value = TextFieldState.WARNING },
            EMAIL_VALIDATION_EXCEPTION_CODE to { _emailTextFieldState.value = TextFieldState.WARNING }
        )
}