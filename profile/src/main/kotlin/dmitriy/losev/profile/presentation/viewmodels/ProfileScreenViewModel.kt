package dmitriy.losev.profile.presentation.viewmodels

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.core.core.runOnMain
import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.usecases.ProfileLogInUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogOutUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.ProfileUserDataUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModel(
    errorHandler: ErrorHandler,
    private val profileUserUseCase: ProfileUserUseCase,
    private val profileUserDataUseCase: ProfileUserDataUseCase,
    private val profileLogInUseCase: ProfileLogInUseCase,
    private val profileLogOutUseCase: ProfileLogOutUseCase,
    private val profileNavigationUseCases: ProfileNavigationUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _user = MutableStateFlow<FirebaseUser?>(value = null)
    private val _avatarUri = MutableStateFlow<Uri?>(value = null)
    private val _displayName = MutableStateFlow<String?>(value = null)
    private val _email = MutableStateFlow<String?>(value = null)
    private val _isEmailVerified = MutableStateFlow(value = false)

    val avatarUri = _avatarUri.asStateFlow()
    val displayName = _displayName.asStateFlow()
    val email = _email.asStateFlow()
    val isEmailVerified = _isEmailVerified.asStateFlow()
    val isAuthenticated = _user.mapLatest { user -> user != null }

    fun authentication(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        if (_user.value != null) {
            profileLogOutUseCase.logOut().processing {
                _user.value = null
                _avatarUri.value = null
            }
        } else {
            profileLogInUseCase.logIn(profileNavigationListener).processing()
        }
    }

    fun navigateToEditProfileScreen(profileNavigationListener: ProfileNavigationListener) =
        runOnMain {
            profileNavigationUseCases.navigateToEditProfileScreen(profileNavigationListener).processing()
        }

    fun loadUserData() = runOnBackground {
        profileUserUseCase.getUserWithOutException().processingNullable { user ->
            _user.value = user
            user?.let {
                profileUserDataUseCase.getUserData(user).processing { userData ->
                    _avatarUri.value = userData.avatarUri
                    _displayName.value = "${userData.firstName} ${userData.lastName}"
                    _email.value = userData.email
                    _isEmailVerified.value = userData.isEmailVerified
                }
            }
        }
    }
}