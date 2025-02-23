pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DinDin"
include(":app")
include(":core:ui")
include(":core:data")
include(":feature:transactions")
include(":feature:categories")
include(":feature:home")
include(":feature:bank-accounts")
include(":core:domain")
include(":core:util")
