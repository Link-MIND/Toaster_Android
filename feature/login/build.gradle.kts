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
  implementation(projects.feature.mainfeature)
  implementation(projects.feature.maincontainer)
  implementation(projects.core.datastore)
  implementation(projects.core.auth)
  implementation(projects.domain.linkminddomain)
  implementation(projects.domain.oauthdomain)
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
}
