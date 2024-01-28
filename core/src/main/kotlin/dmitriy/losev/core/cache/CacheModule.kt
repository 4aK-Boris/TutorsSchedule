package dmitriy.losev.core.cache

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cacheModule = module {

    factoryOf(::FileHandler)

    factoryOf(::CachePath)

    factoryOf(::CacheFileHandler)

    factoryOf(::CacheDataHandler)

    factoryOf(::ByteArrayConverter)
}