package dmitriy.losev.firebase.core.exception

import dmitriy.losev.exception.BaseException

class GoogleAuthIsNotSuccessException : BaseException(extraErrorCode = GOOGLE_AUTH_IS_NOT_SUCCESS_EXCEPTION_CODE)
class EmptyUserData : BaseException(extraErrorCode = EMPTY_USER_DATA)
class UploadAvatarException: BaseException(extraErrorCode = UPLOAD_AVATAR_EXCEPTION_CODE)
class TokenAuthenticationException: BaseException(extraErrorCode = TOKEN_AUTHENTICATION_EXCEPTION_CODE)
class DisplayNameIsNullException: BaseException(extraErrorCode = DISPLAY_NAME_IS_NULL_EXCEPTION_CODE)
class AvatarIsNullException: BaseException(extraErrorCode = AVATAR_URI_IS_NULL_EXCEPTION_CODE)
class EmailIsNullException: BaseException(extraErrorCode = EMAIL_IS_NULL_EXCEPTION_CODE)
class UserNotAuthorizationException: BaseException(extraErrorCode = USER_NOT_AUTHORIZATION_EXCEPTION_CODE)
class NoProvidersException: BaseException(extraErrorCode = NO_PROVIDERS_EXCEPTION_CODE)

// add exceptions

class StudentAddException: BaseException(extraErrorCode = STUDENT_ADD_EXCEPTION_CODE)
class GroupAddException: BaseException(extraErrorCode = GROUP_ADD_EXCEPTION_CODE)
class ContactAddException: BaseException(extraErrorCode = CONTACT_ADD_EXCEPTION_CODE)
class SubjectAddException: BaseException(extraErrorCode = SUBJECT_ADD_EXCEPTION_CODE)
class LessonAddException: BaseException(extraErrorCode = LESSON_ADD_EXCEPTION_CODE)
class PeriodAddException: BaseException(extraErrorCode = PERIOD_ADD_EXCEPTION_CODE)
class TaskAddException: BaseException(extraErrorCode = TASK_ADD_EXCEPTION_CODE)

// get exceptions

class NullableStudentException: BaseException(extraErrorCode = NULLABLE_STUDENT_EXCEPTION_CODE)
class NullableContactException: BaseException(extraErrorCode = NULLABLE_CONTACT_EXCEPTION_CODE)
class NullableGroupException: BaseException(extraErrorCode = NULLABLE_GROUP_EXCEPTION_CODE)
class NullableSubjectException: BaseException(extraErrorCode = NULLABLE_SUBJECT_EXCEPTION_CODE)
class NullableLessonException: BaseException(extraErrorCode = NULLABLE_LESSON_EXCEPTION_CODE)
class NullablePeriodException: BaseException(extraErrorCode = NULLABLE_PERIOD_EXCEPTION_CODE)
class NullableTaskException: BaseException(extraErrorCode = NULLABLE_TASK_EXCEPTION_CODE)

//firebase internal exceptions

class FirebaseAuthInvalidUserException: BaseException(extraErrorCode = FIREBASE_AUTH_INVALID_USER_EXCEPTION_CODE)
class FirebaseAuthInvalidCredentialsException: BaseException(extraErrorCode = FIREBASE_AUTH_INVALID_CREDENTIALS_EXCEPTION_CODE)
class FirebaseTooManyRequestsException: BaseException(extraErrorCode = FIREBASE_TOO_MANY_REQUESTS_EXCEPTION_CODE)
class UnsupportedApiCallException: BaseException(extraErrorCode = UNSUPPORTED_API_CALL_EXCEPTION)
class FirebaseAuthUserCollisionException: BaseException(extraErrorCode = FIREBASE_AUTH_USER_COLLISION_EXCEPTION_CODE)
class FirebaseAuthRecentLoginRequiredException: BaseException(extraErrorCode = FIREBASE_AUTH_RECENT_LOGIN_REQUIRED_EXCEPTION_CODE)
class FirebaseAuthWeakPasswordException: BaseException(extraErrorCode = FIREBASE_AUTH_WEAK_PASSWORD_EXCEPTION_CODE)
class ApiException: BaseException(extraErrorCode = API_EXCEPTION_CODE)
class FirebaseNetworkException: BaseException(extraErrorCode = FIREBASE_NETWORK_EXCEPTION_CODE)
