plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.gms)
}

android {
    namespace = "dmitriy.losev.tutorsschedule"
    compileSdk = 34

    defaultConfig {
        applicationId = "dmitriy.losev.tutorsschedule"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        manifestPlaceholders["YANDEX_CLIENT_ID"] = "406950dcb4dc40bc861689311d404b48"

        manifestPlaceholders["VkExternalAuthRedirectScheme"] = "vk" + "51718394"
        manifestPlaceholders["VkExternalAuthRedirectHost"] = "vk.com"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.ui.viewbinding)
    implementation(libs.material.icons)

    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material.material3)

    implementation(libs.vk.auth.oauth)
    implementation(libs.vk.auth.pub)

    implementation(libs.play.services.auth)

    implementation(libs.coil.compose)

    implementation(libs.accompanist.swiperefresh)

    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.auth.ktx)

    implementation(libs.firebase.ui.auth)
    implementation(libs.firebase.ui.database)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    implementation(project(":auth"))
    implementation(project(":profile"))
    implementation(project(":students"))
    implementation(project(":core"))
    implementation(project(":firebase"))
    implementation(project(":network"))
    implementation(project(":exception"))
    implementation(project(":datastore"))

    implementation(libs.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit.junit5)

    androidTestImplementation(libs.ui.test.junit4)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}