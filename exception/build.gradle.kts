plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.koin.core)
}