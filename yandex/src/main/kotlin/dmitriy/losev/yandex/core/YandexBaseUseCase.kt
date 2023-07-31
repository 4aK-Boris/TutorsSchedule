package dmitriy.losev.yandex.core


import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.exception.ErrorHandler
import kotlin.reflect.KClass

abstract class YandexBaseUseCase(errorHandler: ErrorHandler) :
    BaseUseCase(errorHandler = errorHandler) {

    override val exceptions: Map<KClass<out Throwable>, Int> = mapOf()
}