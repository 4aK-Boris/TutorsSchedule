package dmitriy.losev.profile.presentation.viewmodels

import dmitriy.losev.core.models.Subject
import dmitriy.losev.profile.core.DEFAULT_DISPLAY_NAME
import dmitriy.losev.profile.core.DEFAULT_EMAIL
import dmitriy.losev.profile.core.DEFAULT_PHONE_NUMBER
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.models.UserData
import dmitriy.losev.profile.domain.usecases.ProfileAuthUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetSubjectsUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromInternetUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetUserDataUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileScreenViewModel(
    private val profileAuthUseCase: ProfileAuthUseCase,
    private val profileGetUserDataUseCase: ProfileGetUserDataUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val profileGetSubjectsUseCase: ProfileGetSubjectsUseCase,
    private val profileGetAvatarFromCacheUseCase: ProfileGetAvatarFromCacheUseCase,
    private val profileGetAvatarFromInternetUseCase: ProfileGetAvatarFromInternetUseCase
) : BaseViewModel() {

    private val _avatar = MutableStateFlow<ByteArray?>(value = null)
    private val _displayName = MutableStateFlow(value = DEFAULT_DISPLAY_NAME)
    private val _email = MutableStateFlow(value = DEFAULT_EMAIL)
    private val _phoneNumber = MutableStateFlow(value = DEFAULT_PHONE_NUMBER)

    private val _subjects = MutableStateFlow(value = emptyList<Subject>())

    val avatar = _avatar.asStateFlow()
    val displayName = _displayName.asStateFlow()
    val email = _email.asStateFlow()
    val phoneNumber = _phoneNumber.asStateFlow()

    val subjects = _subjects.asStateFlow()

    fun checkAuthAndLoadData(profileNavigationListener: ProfileNavigationListener) = runOnIO {
        safeCall { profileAuthUseCase.isAuth() }.processing { isAuth ->
            if (isAuth) {
                getUserData()
                getSubjects()
            } else {
                profileNavigationUseCases.navigateToAuthenticationScreen(profileNavigationListener)
            }
        }
    }

    fun navigateToEditProfileScreen(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener, uri = null)
    }

    fun navigateToSubjectsScreen(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.navigateToSubjectsScreen(profileNavigationListener)
    }

    private fun getSubjects() = runOnIO {
        safeCall { profileGetSubjectsUseCase.getSubjects(::onSubjectsLoading) }.processing()
    }

    private fun getUserData() = runOnIOWithLoading {
        safeCall { profileGetUserDataUseCase.getUserData(::onDataLoadingFromCache, ::onDataLoadingFromFirebase) }.processingLoading()
    }

    private fun onDataLoadingFromCache(userData: UserData?) = runOnIO {
        userData?.let {
            stopLoading()
            _displayName.value = userData.displayName ?: DEFAULT_DISPLAY_NAME
            _email.value = userData.email ?: DEFAULT_EMAIL
            _phoneNumber.value = userData.phoneNumber ?: DEFAULT_PHONE_NUMBER
            safeNullableCall { profileGetAvatarFromCacheUseCase.getAvatar() }.processing { avatar ->
                _avatar.value = avatar
            }
        }
    }

    private fun onDataLoadingFromFirebase(userData: UserData) = runOnIO {
        stopLoading()
        _displayName.value = userData.displayName ?: DEFAULT_DISPLAY_NAME
        _email.value = userData.email ?: DEFAULT_EMAIL
        _phoneNumber.value = userData.phoneNumber ?: DEFAULT_PHONE_NUMBER
        safeNullableCall { profileGetAvatarFromInternetUseCase.getAvatar(userData.avatarUri) }.processing { avatar ->
            if (_avatar.value?.contentEquals(avatar)?.not() != false) {
                _avatar.value = avatar
            }
        }
    }

    private fun onSubjectsLoading(subjects: List<Subject>) {
        if (_subjects.value != subjects) {
            _subjects.value = subjects
        }
    }
}