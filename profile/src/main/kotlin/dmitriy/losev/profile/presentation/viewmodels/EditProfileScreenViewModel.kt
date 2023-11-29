package dmitriy.losev.profile.presentation.viewmodels

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.core.core.runOnMain
import dmitriy.losev.core.domain.usecases.ToastUseCase
import dmitriy.losev.firebase.core.exception.FIREBASE_AUTH_RECENT_LOGIN_REQUIRED_EXCEPTION_CODE
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.ButtonBackgroundState
import dmitriy.losev.profile.core.ButtonState
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.core.exception.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.profile.domain.usecases.ProfileDeleteAccountUseCase
import dmitriy.losev.profile.domain.usecases.ProfileEmailVerificationUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAllUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAvatarUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateDisplayNameUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUserDataUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class EditProfileScreenViewModel(
    errorHandler: ErrorHandler,
    private val profileEmailVerificationUseCase: ProfileEmailVerificationUseCase,
    private val profileUpdateDisplayNameUseCase: ProfileUpdateDisplayNameUseCase,
    private val profileUpdateAvatarUseCase: ProfileUpdateAvatarUseCase,
    private val profileUpdateAllUseCase: ProfileUpdateAllUseCase,
    private val profileUserUseCase: ProfileUserUseCase,
    private val profileUserDataUseCase: ProfileUserDataUseCase,
    private val profileUpdateEmailUseCase: ProfileUpdateEmailUseCase,
    private val profileDeleteAccountUseCase: ProfileDeleteAccountUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val toastUseCase: ToastUseCase
) : BaseViewModel(errorHandler = errorHandler) {

    private val _user = MutableStateFlow<FirebaseUser?>(value = null)
    private val _avatarUri = MutableStateFlow<Uri?>(value = null)
    private val _firstName = MutableStateFlow(value = "")
    private val _lastName = MutableStateFlow(value = "")
    private val _email = MutableStateFlow(value = "")
    private val _provider = MutableStateFlow<String?>(value = null)
    private val _isEmailVerified = MutableStateFlow(value = false)
    private val _emailTimer = MutableStateFlow(value = 0)
    private val _emailConfirmDialogState = MutableStateFlow(value = false)
    private val _deleteAccountDialogState = MutableStateFlow(value = false)
    private val _cancelDialogState = MutableStateFlow(value = false)

    private val _buttonBackgroundState = MutableStateFlow(value = ButtonBackgroundState.DEFAULT)
    private val _firstNameButtonBackgroundState = MutableStateFlow(value = ButtonState.DEFAULT)
    private val _lastNameButtonBackgroundState = MutableStateFlow(value = ButtonState.DEFAULT)
    private val _emailButtonBackgroundState = MutableStateFlow(value = ButtonState.DEFAULT)

    private var oldAvatarUri = _avatarUri.value
    private var oldFirstName = _firstName.value
    private var oldLastName = _lastName.value
    private var oldEmail = _email.value

    val avatarUri = _avatarUri.asStateFlow()
    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val email = _email.asStateFlow()
    val emailTimer = _emailTimer.asStateFlow()
    val emailConfirmDialogState = _emailConfirmDialogState.asStateFlow()
    val deleteAccountDialogState = _deleteAccountDialogState.asStateFlow()
    val cancelDialogState = _cancelDialogState.asStateFlow()

    val buttonState = _buttonBackgroundState.asStateFlow()
    val firstNameButtonState = _firstNameButtonBackgroundState.asStateFlow()
    val lastNameButtonState = _lastNameButtonBackgroundState.asStateFlow()
    val emailButtonState = _emailButtonBackgroundState.asStateFlow()

    val isAvatarChanged = _avatarUri.mapLatest { uri ->
        uri != oldAvatarUri
    }

    val isFirstNameChanged = _firstName.mapLatest { firstName ->
        firstName != oldFirstName
    }

    val isLastNameChanged = _lastName.mapLatest { lastName ->
        lastName != oldLastName
    }

    val isEmailChanged = _email.mapLatest { email ->
        email != oldEmail
    }

    private val hasChanges = combine(
        isAvatarChanged,
        isFirstNameChanged,
        isLastNameChanged,
        isEmailChanged
    ) { isAvatarChanged, isFirstNameChanged, isLastNameChanged, isEmailChanged ->
        !isAvatarChanged && !isFirstNameChanged && !isLastNameChanged && !isEmailChanged
    }

    val hasDelayExpired = _emailTimer.mapLatest { seconds ->
        seconds == 0
    }

    val hasNeedEmailVerified = _isEmailVerified.mapLatest { isEmailVerified ->
        oldEmail.isNotBlank() && !isEmailVerified
    }

    val changePassword = _provider.mapLatest { provider ->
        provider == PASSWORD_PROVIDER
    }

    fun openEmailConfirmDialog() {
        _emailConfirmDialogState.value = true
    }

    fun closeEmailConfirmDialog() {
        _emailConfirmDialogState.value = false
    }

    fun openDeleteAccountDialog() {
        _deleteAccountDialogState.value = true
    }

    fun closeDeleteAccountDialog() {
        _deleteAccountDialogState.value = false
    }

    fun openCanselDialog() {
        _cancelDialogState.value = true
    }

    fun closeCanselDialog() {
        _cancelDialogState.value = false
    }

    fun onUriChanged(uri: Uri?) {
        _avatarUri.value = uri
    }

    fun onFirstNameChanged(firstName: String) {
        _firstName.value = firstName
        _firstNameButtonBackgroundState.value = ButtonState.DEFAULT
    }

    fun onLastNameChanged(lastName: String) {
        _lastName.value = lastName
        _lastNameButtonBackgroundState.value = ButtonState.DEFAULT
    }

    fun onEmailChanged(email: String) {
        _email.value = email
        _emailButtonBackgroundState.value = ButtonState.DEFAULT
    }

    private fun onUriUpdate(uri: Uri?) {
        _avatarUri.value = uri
        oldAvatarUri = uri
    }

    private fun onFirstNameUpdate(firstName: String?) {
        firstName?.let {
            _firstName.value = firstName
            oldFirstName = firstName
        }
    }

    private fun onLastNameUpdate(lastName: String?) {
        lastName?.let {
            _lastName.value = lastName
            oldLastName = lastName
        }
    }

    private fun onEmailUpdate(email: String?) {
        email?.let {
            _email.value = email
            oldEmail = email
        }
    }

    fun updateFirstName() = runOnBackground {
        _user.value?.let { user ->
            val firstname = _firstName.value
            profileUpdateDisplayNameUseCase.updateDisplayName(
                user = user,
                firstName = firstname,
                lastName = oldLastName
            ).processing(
                onError = {
                    _firstNameButtonBackgroundState.value = ButtonState.ERROR
                },
                onSuccess = {
                    oldFirstName = firstname
                    _firstNameButtonBackgroundState.value = ButtonState.SUCCESS
                }
            )
        }
    }

    fun updateLastName() = runOnBackground {
        _user.value?.let { user ->
            val lastname = _lastName.value
            profileUpdateDisplayNameUseCase.updateDisplayName(
                user = user,
                firstName = oldFirstName,
                lastName = lastname
            ).processing(
                onError = {
                    _lastNameButtonBackgroundState.value = ButtonState.ERROR
                },
                onSuccess = {
                    oldLastName = lastname
                    _lastNameButtonBackgroundState.value = ButtonState.SUCCESS
                }
            )
        }
    }

    fun updateEmail() = runOnBackground {
        _user.value?.let { user ->
            val email = _email.value
            profileUpdateEmailUseCase.updateEmail(user, email).processing(
                onError = {
                    _emailButtonBackgroundState.value = ButtonState.ERROR
                },
                onSuccess = {
                    oldEmail = email
                    _emailButtonBackgroundState.value = ButtonState.SUCCESS
                }
            )
        }
    }

    fun loadUserData(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        profileUserUseCase.getUserWithException().processing(
            onError = {
                navigateToProfileScreen(profileNavigationListener)
            }
        ) { user ->
            _user.value = user
            profileUserDataUseCase.getUserData(user).processing { userData ->
                onUriUpdate(userData.avatarUri)
                onFirstNameUpdate(userData.firstName)
                onLastNameUpdate(userData.lastName)
                onEmailUpdate(userData.email)
                _provider.value = userData.provider
                _isEmailVerified.value = userData.isEmailVerified
            }
        }
    }

    fun sendEmailVerification() = runOnBackground {
        _user.value?.let { user ->
            profileEmailVerificationUseCase.sendEmailVerification(user).processing {
                repeat(times = EMAIL_TIMER_DELAY) { delay ->
                    _emailTimer.value = EMAIL_TIMER_DELAY - delay
                    delay(1000L)
                }
                _emailTimer.value = 0
            }
        }
    }

    fun deleteAccount(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        _user.value?.let { user ->
            profileDeleteAccountUseCase.deleteAccount(user).processing(
                onError = ::closeDeleteAccountDialog
            ) {
                closeDeleteAccountDialog()
                navigateToProfileScreen(profileNavigationListener)
            }
        }
    }

    fun saveChanges(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        _user.value?.let { user ->
            val avatarUri = _avatarUri.value
            val firstName = _firstName.value
            val lastName = _lastName.value
            val email = _email.value
            isEmailChanged.collectLatest { isEmailChanged ->
                if (isEmailChanged) {
                    profileUpdateAllUseCase.updateAll(user, avatarUri, firstName, lastName, email)
                        .processing {
                            toastUseCase.showToast(id = R.string.confirm_toast_text)
                            navigateToProfileScreen(profileNavigationListener)
                        }
                } else {
                    profileUpdateAvatarUseCase.updateAvatar(user, avatarUri, oldAvatarUri).processing {
                        profileUpdateDisplayNameUseCase.updateDisplayName(user, firstName, lastName)
                            .processing {
                                profileNavigationUseCases.navigateToProfileScreen(
                                    profileNavigationListener
                                ).processing()
                            }

                    }
                }
            }
        }
    }

    fun cansel(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        hasChanges.collectLatest { hasChanges ->
            if (hasChanges) {
                navigateToProfileScreen(profileNavigationListener)
            } else {
                openCanselDialog()
            }
        }
    }

    fun noSaveChanges(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        closeCanselDialog()
        navigateToProfileScreen(profileNavigationListener)
    }

    private fun navigateToProfileScreen(profileNavigationListener: ProfileNavigationListener) =
        runOnMain {
            profileNavigationUseCases.navigateToProfileScreen(profileNavigationListener)
                .processing()
        }

    fun navigateToChangePasswordScreen(profileNavigationListener: ProfileNavigationListener) =
        runOnMain {
            profileNavigationUseCases.navigateToChangePasswordScreen(profileNavigationListener)
                .processing()
        }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            FIREBASE_AUTH_RECENT_LOGIN_REQUIRED_EXCEPTION_CODE to R.string.firebase_auth_exception_message_in_edit_profile_screen,
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception
        )

    companion object {

        private const val EMAIL_TIMER_DELAY = 60

        private const val PASSWORD_PROVIDER = "password"
    }
}