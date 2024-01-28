package dmitriy.losev.profile.domain.di

import dmitriy.losev.profile.domain.usecases.ProfileAuthUseCase
import dmitriy.losev.profile.domain.usecases.ProfileChangeEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileChangePasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckEmailUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckPasswordUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckPhoneNumberUseCase
import dmitriy.losev.profile.domain.usecases.ProfileConvertUriUseCase
import dmitriy.losev.profile.domain.usecases.ProfileDeleteAccountUseCase
import dmitriy.losev.profile.domain.usecases.ProfileEmailAndPasswordChangedUseCase
import dmitriy.losev.profile.domain.usecases.ProfileEmailVerificationUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetNameUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetPhoneNumberUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetSubjectsUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogInUseCase
import dmitriy.losev.profile.domain.usecases.ProfileLogOutUseCase
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.ProfilePhotoPickerUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAvatarUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetAvatarFromInternetUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetUserDataFromCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetUserDataFromFirebaseUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileGetUserDataUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileSaveAvatarToCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileSaveUserDataToCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileUpdateUserDataInCacheUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileUpdateUserDataInFirebaseUseCase
import dmitriy.losev.profile.domain.usecases.data.ProfileUpdateUserDataUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileUseCaseModule = module {

    includes(cameraUseCaseModule, settingsUseCaseModule)

    factoryOf(::ProfileNavigationUseCases)

    factoryOf(::ProfileCheckEmailUseCase)

    factoryOf(::ProfileChangePasswordUseCase)

    factoryOf(::ProfileCheckPasswordUseCase)

    factoryOf(::ProfileUpdateAvatarUseCase)

    factoryOf(::ProfileUpdateUserDataInFirebaseUseCase)

    factoryOf(::ProfileEmailVerificationUseCase)

    factoryOf(::ProfileDeleteAccountUseCase)

    factoryOf(::ProfileGetUserDataFromFirebaseUseCase)

    factoryOf(::ProfileLogInUseCase)

    factoryOf(::ProfileLogOutUseCase)

    factoryOf(::ProfileGetNameUseCase)

    factoryOf(::ProfileGetPhoneNumberUseCase)

    factoryOf(::ProfileAuthUseCase)

    factoryOf(::ProfileChangeEmailUseCase)

    factoryOf(::ProfileEmailAndPasswordChangedUseCase)

    factoryOf(::ProfileGetSubjectsUseCase)

    factoryOf(::ProfileCheckPhoneNumberUseCase)

    factoryOf(::ProfilePhotoPickerUseCase)

    factoryOf(::ProfileConvertUriUseCase)

    factoryOf(::ProfileGetUserDataFromCacheUseCase)

    factoryOf(::ProfileSaveUserDataToCacheUseCase)

    factoryOf(::ProfileGetUserDataUseCase)

    factoryOf(::ProfileGetAvatarFromCacheUseCase)

    factoryOf(::ProfileSaveAvatarToCacheUseCase)

    factoryOf(::ProfileGetAvatarFromInternetUseCase)

    factoryOf(::ProfileUpdateUserDataInCacheUseCase)

    factoryOf(::ProfileUpdateUserDataUseCase)
}