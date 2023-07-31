pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "TutorsSchedule"
include(":app")
include(":core")
include(":auth")
include(":firebase")
include(":network")
include(":exception")
include(":yandex")
include(":firebase:vk")
