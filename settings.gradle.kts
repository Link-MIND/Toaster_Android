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
include(":domain:linkminddomain")
include(":data:linkminddata")
include(":feature:mainfeature")
include(":core:datastore")
include(":core:oauth")
include(":data:oauthdata")
include(":domain:oauthdomain")
include(":data-remote:linkminddata-remote")
include(":data-local:linkminddata-local")
