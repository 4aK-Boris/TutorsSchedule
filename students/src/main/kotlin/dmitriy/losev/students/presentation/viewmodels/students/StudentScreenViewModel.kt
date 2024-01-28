package dmitriy.losev.students.presentation.viewmodels.students

import dmitriy.losev.core.models.Contact
import dmitriy.losev.core.models.Student
import dmitriy.losev.students.R
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.CONTACT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.ContactIsNotLoadingException
import dmitriy.losev.students.core.exception.EMPTY_PHONE_NUMBER_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.contact.DeleteContactUseCase
import dmitriy.losev.students.domain.usecases.contact.GetContactsUseCase
import dmitriy.losev.students.domain.usecases.contacts.CallPhoneUseCase
import dmitriy.losev.students.domain.usecases.contacts.SmsUseCase
import dmitriy.losev.students.domain.usecases.contacts.TelegramUseCase
import dmitriy.losev.students.domain.usecases.contacts.ViberAppUseCase
import dmitriy.losev.students.domain.usecases.contacts.WhatsAppUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class StudentScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getStudentUseCase: GetStudentUseCase,
    private val getContactsUseCase: GetContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val callPhoneUseCase: CallPhoneUseCase,
    private val viberAppUseCase: ViberAppUseCase,
    private val whatsAppUseCase: WhatsAppUseCase,
    private val telegramUseCase: TelegramUseCase,
    private val smsUseCase: SmsUseCase,
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase
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

    private val _commentVisible = MutableStateFlow(value = false)

    private val _contactPopUpVisible = MutableStateFlow(value = false)
    private val _contactCallPopUpVisible = MutableStateFlow(value = false)
    private val _contactMessagePopUpVisible = MutableStateFlow(value = false)

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

    val commentVisible = _commentVisible.asStateFlow()

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

    private fun onEmailChanged(email: String) {
        _email.value = email
    }

    private fun onSkypeChanged(skype: String) {
        _skype.value = skype
    }

    private fun onDiscordChanged(discord: String) {
        _discord.value = discord
    }

    private fun onCommentChanged(comment: String) {
        _comment.value = comment
    }

    private fun onContactsChanged(contacts: List<Contact>) {
        _contacts.value = contacts
    }

    fun onCommentVisibleChanged() {
        _commentVisible.value = _commentVisible.value.not()
    }

    fun onContactChanged(contact: Contact) {
        _contact.value = contact
        openContactPopUp()
    }

    private fun openContactPopUp() {
        _contactPopUpVisible.value = true
    }

    fun closeContactPopUp() {
        _contactPopUpVisible.value = false
    }

    fun openContactCallPopUp() = runOnIO {
        closeContactPopUp()
        checkContactForNullable { contact ->
            safeCall { studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber = contact.phoneNumber) }.processing {
                _contactCallPopUpVisible.value = true
            }
        }
    }

    fun closeContactCallPopUp() {
        _contactCallPopUpVisible.value = false
    }

    fun openContactMessagePopUp() {
        closeContactPopUp()
        checkContactForNullable { contact ->
            safeCall { studentsCheckPhoneNumberUseCase.checkPhoneNumberForEmpty(phoneNumber = contact.phoneNumber) }.processing {
                _contactMessagePopUpVisible.value = true
            }
        }
    }

    fun closeContactMessagePopUp() {
        _contactMessagePopUpVisible.value = false
    }

    fun onPhoneCallClicked() = runOnIO {
        closeContactCallPopUp()
        checkContactForNullable { contact ->
            safeCall { callPhoneUseCase.openApplication(phoneNumber = contact.phoneNumber) }.processing()
        }
    }

    fun onViberClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        checkContactForNullable { contact ->
            safeCall { viberAppUseCase.openApplication(phoneNumber = contact.phoneNumber) }.processing()
        }
    }

    fun onWhatsAppClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        checkContactForNullable { contact ->
            safeCall { whatsAppUseCase.openApplication(phoneNumber = contact.phoneNumber) }.processing()
        }
    }

    fun onTelegramClicked() = runOnIO {
        closeContactCallPopUp()
        closeContactMessagePopUp()
        checkContactForNullable { contact ->
            safeCall { telegramUseCase.openApplication(phoneNumber = contact.phoneNumber) }.processing()
        }
    }

    fun onSmsClicked() = runOnIO {
        closeContactMessagePopUp()
        checkContactForNullable { contact ->
            safeCall { smsUseCase.openApplication(phoneNumber = contact.phoneNumber) }.processing()
        }
    }

    fun deleteContact(studentId: String) = runOnIOWithLoading {
        closeContactPopUp()
        checkContactForNullable { contact ->
            safeCall { deleteContactUseCase.deleteContact(studentId, contact.id!!) }.processingLoading {
                _contacts.value = _contacts.value.toMutableList().apply {
                    remove(contact)
                }
            }
        }
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun navigateToUpdateStudentScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        studentsNavigationUseCases.navigateToUpdateStudentScreen(studentsNavigationListener, studentId)
    }

    fun navigateToAddContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        studentsNavigationUseCases.navigateToAddContactScreen(studentsNavigationListener, studentId)
    }

    fun navigateToUpdateContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) = runOnMain {
        closeContactPopUp()
        checkContactForNullable { contact ->
            studentsNavigationUseCases.navigateToUpdateContactScreen(studentsNavigationListener, studentId, contact.id!!)
        }
    }

    fun navigateToContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) = runOnMain {
        studentsNavigationUseCases.navigateToContactScreen(studentsNavigationListener, studentId, contactId)
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

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            CONTACT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.contact_is_not_loading_exception_message,
            EMPTY_PHONE_NUMBER_EXCEPTION_CODE to R.string.empty_phone_number_exception_message2,
            WHATS_APP_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.whats_app_application_is_not_installed_exception_message,
            VIBER_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.viber_application_is_not_installed_exception_message,
            TELEGRAM_APPLICATION_IS_NOT_INSTALLED_EXCEPTION_CODE to R.string.telegram_application_is_not_installed_exception_message
        )
}