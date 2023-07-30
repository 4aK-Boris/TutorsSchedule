package dmitriy.losev.exception

import org.koin.dsl.module

val exceptionModule = module {

    single {
        ErrorHandler()
    }
}