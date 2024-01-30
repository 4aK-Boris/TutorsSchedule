package dmitriy.losev.students.presentation.viewmodels.contact

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_FIRST_NAME_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.PHONE_NUMBER_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contact.AddContactUseCase
import dmitriy.losev.students.domain.usecases.contacts.ClearPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.contacts.PickContactUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddContactScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val pickContactUseCase: PickContactUseCase,
    private val clearPhoneNumberUseCase: ClearPhoneNumberUseCase,
    private val addContactUseCase: AddContactUseCase
) : BaseViewModel() {

    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)
    private val _phoneNumber = MutableStateFlow(value = EMPTY_STRING)

    private val _firstNameTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)
    private val _phoneNumberTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val patronymic = _patronymic.asStateFlow()
    val phoneNumber = _phoneNumber.asStateFlow()

    val firstNameTextFieldState = _firstNameTextFieldState.asStateFlow()
    val phoneNumberTextFieldState = _phoneNumberTextFieldState.asStateFlow()

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

    fun clearFirstNameTextFieldState() {
        _firstNameTextFieldState.value = TextFieldState.DEFAULT
    }

    fun clearPhoneNumberTextFieldState() {
        _phoneNumberTextFieldState.value = TextFieldState.DEFAULT
    }

    fun saveContact(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnIOWithLoading {
        safeCall {
            addContactUseCase.addContact(
                studentId = studentId,
                firstName = _firstName.value,
                lastName = _lastName.value,
                patronymic = _patronymic.value,
                phoneNumber = _phoneNumber.value
            )
        }.processingLoading {
            back(studentsNavigationListener)
        }
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun pickContactIntent(permissionState: PermissionState, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = runOnIO {
        safeCall { pickContactUseCase.pickContactIntent(permissionState, launcher) }.processing()
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

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to R.string.empty_first_name_exception_message,
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to R.string.phone_number_validation_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to { _firstNameTextFieldState.value = TextFieldState.WARNING },
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to { _phoneNumberTextFieldState.value = TextFieldState.WARNING  }
        )
}