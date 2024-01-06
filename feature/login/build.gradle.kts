@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.plugin.feature)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "org.sopt.login"
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(projects.core.datastore)
  implementation(projects.core.auth)
  implementation(projects.domain.linkminddomain)
  implementation(projects.domain.oauthdomain)
  implementation(projects.core.designsystem)
  implementation(projects.feature.maincontainer)
}
