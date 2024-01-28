package dmitriy.losev.vk.domain.usecases

import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePatronymicUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePhoneNumberUseCase
import dmitriy.losev.vk.core.EMPTY_STRING
import dmitriy.losev.vk.core.VkBaseUseCase

class VkUpdateInformationUseCase(
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseUpdateFirstNameUseCase: FirebaseUpdateFirstNameUseCase,
    private val firebaseUpdateLastNameUseCase: FirebaseUpdateLastNameUseCase,
    private val firebaseUpdatePatronymicUseCase: FirebaseUpdatePatronymicUseCase,
    private val firebaseUpdatePhoneNumberUseCase: FirebaseUpdatePhoneNumberUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
) : VkBaseUseCase() {

    suspend fun updateInformation(avatarUrl: String?, firstName: String, lastName: String, email: String?) = launchFun(
        f1 = { firebaseUpdateAvatarUseCase.updateAvatar(avatarUrl) },
        f2 = { firebaseUpdateFirstNameUseCase.updateFirstName(firstName) },
        f3 = { firebaseUpdateLastNameUseCase.updateLastName(lastName) },
        f4 = { firebaseUpdatePatronymicUseCase.updatePatronymic(patronymic = EMPTY_STRING) },
        f5 = { firebaseUpdatePhoneNumberUseCase.updatePhoneNumber(phoneNumber = EMPTY_STRING) },
        f6 = { email?.let { firebaseUpdateEmailUseCase.updateEmail(email) } }
    )
}