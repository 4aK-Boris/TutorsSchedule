package dmitriy.losev.subjects.presentation.viewmodels

import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.core.exception.USER_NOT_AUTHORIZATION_EXCEPTION_CODE
import dmitriy.losev.subjects.R
import dmitriy.losev.subjects.core.EMPTY_STRING
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.core.exception.NAME_EMPTY_EXCEPTION_CODE
import dmitriy.losev.subjects.core.exception.SUBJECT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.subjects.core.exception.SubjectIsNotLoadingException
import dmitriy.losev.subjects.domain.usecases.SubjectsGetSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsNavigationUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsUpdateSubjectUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditSubjectScreenViewModel(
    private val subjectsNavigationUseCase: SubjectsNavigationUseCase,
    private val subjectsUpdateSubjectUseCase: SubjectsUpdateSubjectUseCase,
    private val subjectsGetSubjectUseCase: SubjectsGetSubjectUseCase
) : BaseViewModel() {

    private var subject: Subject? = null

    private val _name = MutableStateFlow(value = EMPTY_STRING)
    private val _price = MutableStateFlow(value = EMPTY_STRING)

    private val _nameTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    val name = _name.asStateFlow()
    val price = _price.asStateFlow()

    val nameTextFieldState = _nameTextFieldState.asStateFlow()

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onPriceChanged(price: String) {
        _price.value = price
    }

    fun clearTextFieldState() {
        _nameTextFieldState.value = TextFieldState.DEFAULT
    }

    fun loadSubject(subjectId: String) = runOnIOWithLoading {
        safeCall { subjectsGetSubjectUseCase.getSubject(subjectId, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    fun saveChanges(subjectsNavigationListener: SubjectsNavigationListener, subjectId: String) = runOnIOWithLoading {
        checkSubjectForNullableAndCopy { subject ->
            safeCall { subjectsUpdateSubjectUseCase.updateSubject(subjectId, subject) }.processingLoading {
                subjectsNavigationUseCase.back(subjectsNavigationListener)
            }
        }
    }

    fun back(subjectsNavigationListener: SubjectsNavigationListener) = runOnMain {
        subjectsNavigationUseCase.back(subjectsNavigationListener)
    }

    private fun onDatabaseLoading(subject: Subject?) {
        subject?.let { onFirebaseLoading(subject) }
    }

    private fun onFirebaseLoading(subject: Subject) {
        stopLoading()
        if (this.subject != subject) {
            this.subject = subject
            _name.value = subject.name
            _price.value = subject.price
        }
    }

    private fun checkSubjectForNullableAndCopy(f: suspend (Subject) -> Unit) = runOnIO {
        safeCall { subject ?: throw SubjectIsNotLoadingException() }.processing { subject ->
            f(subject.copy(name = _name.value, price = _price.value))
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            NAME_EMPTY_EXCEPTION_CODE to R.string.name_empty_exception_message,
            USER_NOT_AUTHORIZATION_EXCEPTION_CODE to R.string.user_not_authorization_exception_message,
            SUBJECT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.subject_is_not_loading_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(NAME_EMPTY_EXCEPTION_CODE to { _nameTextFieldState.value = TextFieldState.ERROR })
}