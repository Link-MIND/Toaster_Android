pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
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

rootProject.name = "Link-Mind"
include(":app")
include(":core:core-network")
include(":core:core-common")
include(":core:core-model")
include(":core:core-ui")
include(":core:core-auth")
include(":core:core-auth-impl")
