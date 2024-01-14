@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.plugin.feature)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "org.sopt.home"
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(projects.domain.home)
  implementation(libs.coil)
  implementation(libs.jsoup)
}
