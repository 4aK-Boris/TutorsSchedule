package dmitriy.losev.students.presentation.viewmodels.contact

import dmitriy.losev.core.models.Contact
import dmitriy.losev.students.R
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_PHONE_NUMBER_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.SKYPE_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contact.DeleteContactUseCase
import dmitriy.losev.students.domain.usecases.contact.GetContactUseCase
import dmitriy.losev.students.domain.usecases.contacts.CallPhoneUseCase
import dmitriy.losev.students.domain.usecases.contacts.SmsUseCase
import dmitriy.losev.students.domain.usecases.contacts.TelegramUseCase
import dmitriy.losev.students.domain.usecases.contacts.ViberAppUseCase
import dmitriy.losev.students.domain.usecases.contacts.WhatsAppUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getContactUseCase: GetContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val callPhoneUseCase: CallPhoneUseCase,
    private val viberAppUseCase: ViberAppUseCase,
    private val whatsAppUseCase: WhatsAppUseCase,
    private val telegramUseCase: TelegramUseCase,
    private val smsUseCase: SmsUseCase,
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase
) : BaseViewModel() {

    private var contact: Contact? = null

    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)
    private val _phoneNumber = MutableStateFlow(value = EMPTY_STRING)

    private val _shortName = MutableStateFlow(value = EMPTY_STRING)

    private val _contactPopUpVisible = MutableStateFlow(value = false)
    private val _contactCallPopUpVisible = MutableStateFlow(value = false)
    private val _contactMessagePopUpVisible = MutableStateFlow(value = false)

    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val patronymic = _patronymic.asStateFlow()
    val phoneNumber = _phoneNumber.asStateFlow()

    val shortName = _shortName.asStateFlow()

    val contactPopUpVisible = _contactPopUpVisible.asStateFlow()
    val contactCallPopUpVisible = _contactCallPopUpVisible.asStateFlow()
    val contactMessagePopUpVisible = _contactMessagePopUpVisible.asStateFlow()

    private fun onFirstNameChanged(firstName: String) {
        _firstName.value = firstName
    }

    private fun onLastNameChanged(lastName: String) {
        _lastName.value = lastName
    }

    private fun onPatronymicChanged(patronymic: String) {
        _patronymic.value = patronymic
    }

    private fun onPhoneNumberChanged(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    private fun onShortNameChanged(shortName: String) {
        _shortName.value = shortName
    }

    fun openContactPopUp() {
        _contactPopUpVisible.value = true
    }

    fun closeContactPopUp() {
        _contactPopUpVisible.value = false
    }

    fun openContactCallPopUp() = runOnIO {
        closeContactPopUp()
        safeCall { studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber = _phoneNumber.value) }.processing {
            _contactCallPopUpVisible.value = true
        }
    }

    fun closeContactCallPopUp() {
        _contactCallPopUpVisible.value = false
    }

    fun openContactMessagePopUp() = runOnIO {
        closeContactPopUp()
        safeCall { studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber = _phoneNumber.value) }.processing {
            _contactMessagePopUpVisible.value = true
        }
    }

    fun closeContactMessagePopUp() {
        _contactMessagePopUpVisible.value = false
    }

    fun loadContact(studentId: String, contactId: String) = runOnIOWithLoading {
        safeCall { getContactUseCase.getContact(studentId, contactId, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    fun deleteContact(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) = runOnIOWithLoading {
        closeContactPopUp()
        safeCall { deleteContactUseCase.deleteContact(studentId, contactId) }.processingLoading {
            back(studentsNavigationListener)
        }
    }

    fun onPhoneCallClicked() = runOnIO {
        closeContactCallPopUp()
        safeCall { callPhoneUseCase.openApplication(phoneNumber = _phoneNumber.value) }.processing()
    }

    fun onViberClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        safeCall { viberAppUseCase.openApplication(phoneNumber = _phoneNumber.value) }.processing()
    }

    fun onWhatsAppClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        safeCall { whatsAppUseCase.openApplication(phoneNumber = _phoneNumber.value) }.processing()
    }

    fun onTelegramClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        safeCall { telegramUseCase.openApplication(phoneNumber = _phoneNumber.value) }.processing()
    }

    fun onSmsClicked() = runOnIO {
        closeContactMessagePopUp()
        safeCall { smsUseCase.openApplication(phoneNumber = _phoneNumber.value) }.processing()
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun navigateToUpdateContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) = runOnMain {
        closeContactPopUp()
        studentsNavigationUseCases.navigateToUpdateContactScreen(studentsNavigationListener, studentId, contactId)
    }

    private fun onFirebaseLoading(contact: Contact) = runOnIO {
        stopLoading()
        updateContact(contact)
    }

    private fun onDatabaseLoading(contact: Contact?) = runOnIO {
        contact?.let { onFirebaseLoading(contact) }
    }

    private fun updateContact(contact: Contact) {
        if (this.contact != contact) {
            onFirstNameChanged(firstName = contact.firstName)
            onLastNameChanged(lastName = contact.lastName)
            onPatronymicChanged(patronymic = contact.patronymic)
            onPhoneNumberChanged(phoneNumber = contact.phoneNumber)
            onShortNameChanged(shortName = contact.shortName)
            this.contact = contact
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_PHONE_NUMBER_EXCEPTION_CODE to R.string.empty_phone_number_exception_message,
            WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.whats_app_application_is_not_installed_exception_message,
            SKYPE_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.skype_application_is_not_installed_exception_message,
            VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.viber_application_is_not_installed_exception_message,
            TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.telegram_application_is_not_installed_exception_message,
        )
}