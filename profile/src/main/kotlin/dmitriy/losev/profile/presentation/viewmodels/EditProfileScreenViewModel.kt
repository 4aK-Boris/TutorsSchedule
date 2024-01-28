package dmitriy.losev.profile.presentation.viewmodels

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import dmitriy.losev.profile.R
import dmitriy.losev.profile.core.DEFAULT_DISPLAY_NAME
import dmitriy.losev.profile.core.DEFAULT_EMAIL
import dmitriy.losev.profile.core.EMPTY_STRING
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.core.exception.EMAIL_VALIDATION_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.EMPTY_EMAIL_EXCEPTION_CODE
import dmitriy.losev.profile.core.exception.USER_AVAILABLE_EXCEPTION_CODE
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.usecases.ProfileAuthUseCase
import dmitriy.losev.profile.domain.usecases.ProfileConvertUriUseCase
import dmitriy.losev.profile.domain.usecases.ProfileDeleteAccountUseCase
import dmitriy.losev.profile.domain.usecases.ProfileEmailVerificationUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogOutUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.ProfilePhotoPickerUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromInternetUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetUserDataUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileUpdateUserDataUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.ui.core.runOnBackgroundWithLoading
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine


class EditProfileScreenViewModel(
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val profileLogOutUseCase: ProfileLogOutUseCase,
    private val profileGetUserDataUseCase: ProfileGetUserDataUseCase,
    private val profileDeleteAccountUseCase: ProfileDeleteAccountUseCase,
    private val profileEmailVerificationUseCase: ProfileEmailVerificationUseCase,
    private val profileUpdateUserDataUseCase: ProfileUpdateUserDataUseCase,
    private val profilePhotoPickerUseCase: ProfilePhotoPickerUseCase,
    private val profileConvertUriUseCase: ProfileConvertUriUseCase,
    private val profileGetAvatarFromCacheUseCase: ProfileGetAvatarFromCacheUseCase,
    private val profileGetAvatarFromInternetUseCase: ProfileGetAvatarFromInternetUseCase,
    private val profileAuthUseCase: ProfileAuthUseCase
) : BaseViewModel() {

    private var fullUserData: FullUserData? = null

    private val _avatarUri = MutableStateFlow<Uri?>(value = null)
    private val _avatar = MutableStateFlow<ByteArray?>(value = null)
    private val _displayName = MutableStateFlow(value = DEFAULT_DISPLAY_NAME)
    private val _email = MutableStateFlow(value = EMPTY_STRING)
    private val _phoneNumber = MutableStateFlow(value = EMPTY_STRING)
    private val _firstName = MutableStateFlow(value = EMPTY_STRING)
    private val _lastName = MutableStateFlow(value = EMPTY_STRING)
    private val _patronymic = MutableStateFlow(value = EMPTY_STRING)

    private val _popUpDeleteVisible = MutableStateFlow(value = false)
    private val _popUpExitVisible = MutableStateFlow(value = false)
    private val _popUpEmailVisible = MutableStateFlow(value = false)
    private val _popUpPhotoVisible = MutableStateFlow(value = false)

    private val _hasEmailChanged = MutableStateFlow(value = false)
    private val _isEmailVerified = MutableStateFlow(value = false)
    private val _hasPasswordChanged = MutableStateFlow(value = false)

    val avatar = combine(_avatarUri, _avatar) { avatarUri, avatar ->
        avatarUri ?: avatar
    }

    val displayName = _displayName.asStateFlow()
    val email = _email.asStateFlow()
    val firstName = _firstName.asStateFlow()
    val lastName = _lastName.asStateFlow()
    val patronymic = _patronymic.asStateFlow()
    val phoneNumber = _phoneNumber.asStateFlow()

    val popUpDeleteVisible = _popUpDeleteVisible.asStateFlow()
    val popUpExitVisible = _popUpExitVisible.asStateFlow()
    val popUpEmailVisible = _popUpEmailVisible.asStateFlow()
    val popUpPhotoVisible = _popUpPhotoVisible.asStateFlow()

    val hasEmailChanged = _hasEmailChanged.asStateFlow()
    val isEmailVerified = _isEmailVerified.asStateFlow()
    val hasPasswordChanged = _hasPasswordChanged.asStateFlow()

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

    fun onAvatarUriChanged(avatarUri: Uri?) {
        _avatarUri.value = avatarUri
    }

    fun onAvatarUriChanged(avatarUri: String?) = runOnIO {
        safeNullableCall { profileConvertUriUseCase.convertStringToUri(avatarUri) }.processing { uri ->
            onAvatarUriChanged(uri)
        }
    }

    fun back(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.back(profileNavigationListener)
    }

    fun showDeletePopUp() {
        _popUpDeleteVisible.value = true
    }

    fun closeDeletePopUp() {
        _popUpDeleteVisible.value = false
    }

    fun showExitPopUp() {
        _popUpExitVisible.value = true
    }

    fun closeExitPopUp() {
        _popUpExitVisible.value = false
    }

    fun showEmailPopUp() {
        _popUpEmailVisible.value = true
    }

    fun closeEmailPopUp() {
        _popUpEmailVisible.value = false
    }

    fun showPhotoPopUp() {
        _popUpPhotoVisible.value = true
    }

    fun closePhotoPopUp() {
        _popUpPhotoVisible.value = false
    }

    fun sendMail() = runOnBackground {
        safeCall { profileEmailVerificationUseCase.sendEmailVerification() }.processing {
            closeEmailPopUp()
        }
    }

    fun onEmailEditClick(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        if (_isEmailVerified.value) {
            profileNavigationUseCases.navigateToChangeEmailScreen(profileNavigationListener)
        } else {
            showEmailPopUp()
        }
    }

    fun onPasswordEditClick(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.navigateToChangePasswordScreen(profileNavigationListener)
    }

    fun deleteAccount(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        safeCall { profileDeleteAccountUseCase.deleteAccount() }.processing {
            profileNavigationUseCases.navigateToAuthenticationScreen(profileNavigationListener)
        }
    }

    fun saveChanges(profileNavigationListener: ProfileNavigationListener) = runOnBackgroundWithLoading {
        val avatarUri = _avatarUri.value
        val firstName = _firstName.value
        val lastName = _lastName.value
        val patronymic = _patronymic.value
        val phoneNumber = _phoneNumber.value
        safeCall { profileUpdateUserDataUseCase.updateUserData(
            oldAvatarUri = fullUserData?.avatarUri,
            newAvatarUri = avatarUri,
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            phoneNumber = phoneNumber
        ) }.processingLoading {
            profileNavigationUseCases.back(profileNavigationListener)
        }
    }

    fun loadUserData(profileNavigationListener: ProfileNavigationListener) = runOnIOWithLoading {
        safeCall { profileAuthUseCase.isAuth() }.processing { isAuth ->
            if (isAuth) {
                safeCall {
                    profileGetUserDataUseCase.getFullUserData(onCacheLoading = ::onDataLoadingFromCache, onFirebaseLoading = ::onDataLoadingFromFirebase)
                }.processing()
            } else {
                stopLoading()
                profileNavigationUseCases.navigateToAuthenticationScreen(profileNavigationListener)
            }
        }
    }

    fun logOut(profileNavigationListener: ProfileNavigationListener) = runOnBackground {
        closeExitPopUp()
        safeCall { profileLogOutUseCase.logOut() }.processing {
            profileNavigationUseCases.navigateToAuthenticationScreen(profileNavigationListener)
        }
    }

    fun navigateToSettingsScreen(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.navigateToSettingsScreen(profileNavigationListener)
    }

    fun pickPhoto(launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) = runOnMain {
        closePhotoPopUp()
        profilePhotoPickerUseCase.launch(launcher)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun createPhoto(profileNavigationListener: ProfileNavigationListener, permissionState: MultiplePermissionsState) = runOnMain {
        if (permissionState.allPermissionsGranted) {
            closePhotoPopUp()
            profileNavigationUseCases.navigateToCameraScreen(profileNavigationListener)
        } else {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    private fun onDataLoadingFromCache(fullUserData: FullUserData?) = runOnIO {
        fullUserData?.let {
            onDataLoading(fullUserData)
            safeNullableCall { profileGetAvatarFromCacheUseCase.getAvatar() }.processing { avatar ->
                _avatar.value = avatar
            }
        }
    }

    private fun onDataLoadingFromFirebase(fullUserData: FullUserData) = runOnIO {
        onDataLoading(fullUserData)
        safeNullableCall { profileGetAvatarFromInternetUseCase.getAvatar(fullUserData.avatarUri) }.processing { avatar ->
            if (_avatar.value?.contentEquals(avatar)?.not() != false) {
                _avatar.value = avatar
            }
        }
    }

    private fun onDataLoading(fullUserData: FullUserData) {
        stopLoading()
        if (this.fullUserData != fullUserData) {
            _displayName.value = fullUserData.displayName ?: DEFAULT_DISPLAY_NAME
            _email.value = fullUserData.email ?: DEFAULT_EMAIL
            _phoneNumber.value = fullUserData.phoneNumber ?: EMPTY_STRING
            _firstName.value = fullUserData.firstName
            _lastName.value = fullUserData.lastName
            _patronymic.value = fullUserData.patronymic
            _hasEmailChanged.value = fullUserData.hasEmailAndPasswordChanged
            _hasPasswordChanged.value = fullUserData.hasEmailAndPasswordChanged
            _isEmailVerified.value = fullUserData.isEmailVerified
            this.fullUserData = fullUserData
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_EMAIL_EXCEPTION_CODE to R.string.empty_email_exception_message,
            EMAIL_VALIDATION_EXCEPTION_CODE to R.string.email_validation_exception_message,
            USER_AVAILABLE_EXCEPTION_CODE to R.string.user_available_exception
        )
}