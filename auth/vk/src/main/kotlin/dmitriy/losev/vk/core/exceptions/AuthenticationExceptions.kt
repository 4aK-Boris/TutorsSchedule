package dmitriy.losev.vk.core.exceptions

import dmitriy.losev.exception.BaseException

class VkFailedAuthenticationException: BaseException(extraErrorCode = VK_FAILED_AUTHENTICATION_EXCEPTION_CODE)