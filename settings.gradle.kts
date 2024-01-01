enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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
    maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
  }
}

rootProject.name = "Link-Mind"
include(":app")
include(":core:network")
include(":core:common")
include(":core:model")
include(":core:ui")
include(":core:auth")
include(":core:authimpl")
include(":core:datastore")
include(":domain:linkminddomain")
include(":domain:oauthdomain")
include(":data:linkminddata")
include(":data:oauthdata")
include(":data-remote:linkminddata-remote")
include(":data-local:linkminddata-local")
include(":feature:mainfeature")
