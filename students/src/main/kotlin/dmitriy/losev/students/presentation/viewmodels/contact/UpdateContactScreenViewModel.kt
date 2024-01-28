package dmitriy.losev.students.presentation.viewmodels.contact

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dmitriy.losev.core.models.Contact
import dmitriy.losev.students.R
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.CONTACT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.ContactIsNotLoadingException
import dmitriy.losev.students.core.exception.EMPTY_FIRST_NAME_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.PHONE_NUMBER_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contact.GetContactUseCase
import dmitriy.losev.students.domain.usecases.contact.UpdateContactUseCase
import dmitriy.losev.students.domain.usecases.contacts.ClearPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.contacts.PickContactUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UpdateContactScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val pickContactUseCase: PickContactUseCase,
    private val clearPhoneNumberUseCase: ClearPhoneNumberUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val getContactUseCase: GetContactUseCase
) : BaseViewModel() {

    private var contact: Contact? = null

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

    fun loadContact(studentId: String, contactId: String) = runOnIOWithLoading {
        safeCall { getContactUseCase.getContact(studentId, contactId, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    fun updateContact(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) = runOnIOWithLoading {
        checkContactForNullableAndCopy { contact ->
            safeCall { updateContactUseCase.updateContact(studentId, contactId, contact = contact) }.processingLoading {
                back(studentsNavigationListener)
            }
        }
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    private fun onFirebaseLoading(contact: Contact) = runOnIO {
        stopLoading()
        updateContact(contact)
    }

    private fun onDatabaseLoading(contact: Contact?) = runOnIO {
        contact?.let { onFirebaseLoading(contact) }
    }

    private fun updateContact(contact: Contact) {
        onFirstNameChanged(firstName = contact.firstName)
        onLastNameChanged(lastName = contact.lastName)
        onPatronymicChanged(patronymic = contact.patronymic)
        onPhoneNumberChanged(phoneNumber = contact.phoneNumber)
        this.contact = contact
    }

    private fun checkContactForNullableAndCopy(f: suspend (Contact) -> Unit) = runOnIO {
        safeCall { contact ?: throw ContactIsNotLoadingException() }.processing { contact ->
            f(contact.copy(firstName = _firstName.value, lastName = _lastName.value, patronymic = _patronymic.value, phoneNumber = _phoneNumber.value))
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to R.string.empty_first_name_exception_message,
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to R.string.phone_number_validation_exception_message,
            CONTACT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.contact_is_not_loading_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_FIRST_NAME_EXCEPTION_CODE to { _firstNameTextFieldState.value = TextFieldState.WARNING },
            PHONE_NUMBER_VALIDATION_EXCEPTION_CODE to { _phoneNumberTextFieldState.value = TextFieldState.WARNING  }
        )
}