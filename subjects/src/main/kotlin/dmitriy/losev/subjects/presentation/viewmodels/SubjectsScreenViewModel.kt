package dmitriy.losev.subjects.presentation.viewmodels

import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.core.exception.USER_NOT_AUTHORIZATION_EXCEPTION_CODE
import dmitriy.losev.subjects.R
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.core.exception.SUBJECT_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.subjects.core.exception.SubjectIsNotLoadingException
import dmitriy.losev.subjects.domain.usecases.SubjectsDeleteSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsGetSubjectsUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsNavigationUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SubjectsScreenViewModel(
    private val subjectsNavigationUseCase: SubjectsNavigationUseCase,
    private val subjectsGetSubjectsUseCase: SubjectsGetSubjectsUseCase,
    private val subjectsDeleteSubjectUseCase: SubjectsDeleteSubjectUseCase
) : BaseViewModel() {

    private val _subjects = MutableStateFlow(value = emptyList<Subject>())
    private val _subject = MutableStateFlow<Subject?>(value = null)

    private val _subjectPopUpVisible = MutableStateFlow(value = false)

    val subjects = _subjects.asStateFlow()
    val subject = _subject.asStateFlow()

    val subjectPopUpVisible = _subjectPopUpVisible.asStateFlow()

    fun openSubjectPopUp(subject: Subject) {
        _subjectPopUpVisible.value = true
        _subject.value = subject
    }

    fun closeSubjectPopUp() {
        _subjectPopUpVisible.value = false
    }

    fun loadSubjects() = runOnIOWithLoading {
        safeCall { subjectsGetSubjectsUseCase.getSubjects(::onFirebaseLoading, ::onDatabaseLoading) }.processingLoading()
    }

    fun navigateToProfileScreen(subjectsNavigationListener: SubjectsNavigationListener) = runOnMain {
        subjectsNavigationUseCase.navigateToProfileScreen(subjectsNavigationListener)
    }

    fun navigateToAddSubjectScreen(subjectsNavigationListener: SubjectsNavigationListener) = runOnMain {
        subjectsNavigationUseCase.navigateToAddSubjectScreen(subjectsNavigationListener)
    }

    fun navigateToEditSubjectScreen(subjectsNavigationListener: SubjectsNavigationListener) = runOnMain {
        closeSubjectPopUp()
        checkSubjectForNullable { subject ->
            subjectsNavigationUseCase.navigateToEditSubjectScreen(subjectsNavigationListener, subject.id)
        }
    }

    fun deleteSubject() = runOnBackground {
        closeSubjectPopUp()
        checkSubjectForNullable { subject ->
            safeCall { subjectsDeleteSubjectUseCase.deleteSubject(subject.id) }.processing {
                _subjects.value = _subjects.value.toMutableList().apply {
                    removeIf { s -> s.id == subject.id }
                }
            }
        }
    }

    private fun onDatabaseLoading(subjects: List<Subject>) {
        if (subjects.isNotEmpty()) {
            onFirebaseLoading(subjects)
        }
    }

    private fun onFirebaseLoading(subjects: List<Subject>) {
        stopLoading()
        if (_subjects.value != subjects) {
            _subjects.value = subjects
        }
    }

    private fun checkSubjectForNullable(f: suspend (Subject) -> Unit) = runOnIO {
        safeCall { _subject.value ?: throw SubjectIsNotLoadingException() }.processing { subject ->
            f(subject)
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            USER_NOT_AUTHORIZATION_EXCEPTION_CODE to R.string.user_not_authorization_exception_message,
            SUBJECT_IS_NOT_LOADING_EXCEPTION_CODE to R.string.subject_is_not_loading_exception_message
        )
}