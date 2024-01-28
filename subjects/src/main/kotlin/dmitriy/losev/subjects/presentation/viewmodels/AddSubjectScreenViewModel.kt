package dmitriy.losev.subjects.presentation.viewmodels

import dmitriy.losev.firebase.core.exception.USER_NOT_AUTHORIZATION_EXCEPTION_CODE
import dmitriy.losev.subjects.R
import dmitriy.losev.subjects.core.EMPTY_STRING
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.core.exception.NAME_EMPTY_EXCEPTION_CODE
import dmitriy.losev.subjects.domain.usecases.SubjectsAddSubjectUseCase
import dmitriy.losev.subjects.domain.usecases.SubjectsNavigationUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddSubjectScreenViewModel(
    private val subjectsNavigationUseCase: SubjectsNavigationUseCase,
    private val subjectsAddSubjectUseCase: SubjectsAddSubjectUseCase
) : BaseViewModel() {

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

    fun addSubject(subjectsNavigationListener: SubjectsNavigationListener) = runOnIOWithLoading {
        val name = _name.value
        val price = _price.value
        safeCall { subjectsAddSubjectUseCase.addSubject(name, price) }.processingLoading {
            subjectsNavigationUseCase.back(subjectsNavigationListener)
        }
    }

    fun back(subjectsNavigationListener: SubjectsNavigationListener) = runOnMain {
        subjectsNavigationUseCase.back(subjectsNavigationListener)
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            NAME_EMPTY_EXCEPTION_CODE to R.string.name_empty_exception_message,
            USER_NOT_AUTHORIZATION_EXCEPTION_CODE to R.string.user_not_authorization_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(NAME_EMPTY_EXCEPTION_CODE to { _nameTextFieldState.value = TextFieldState.ERROR })
}