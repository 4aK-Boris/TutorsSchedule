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

rootProject.name = "TutorsSchedule"
include(":app")
include(":core")
include(":auth")
include(":firebase")
include(":network")
include(":exception")
include(":yandex")
include(":firebase:vk")
