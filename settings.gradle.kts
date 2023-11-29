pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://artifactory-external.vkpartner.ru/artifactory/superappkit-maven-public/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://artifactory-external.vkpartner.ru/artifactory/superappkit-maven-public/")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TutorsSchedule"
include(":app")
include(":core")
include(":auth")
include(":firebase")
include(":network")
include(":exception")
include(":auth:vk")
include(":auth:yandex")
include(":auth:yandex")
include(":profile")
include(":students")
include(":datastore")
