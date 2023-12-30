@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.application)
  alias(libs.plugins.linkmind.android.hilt)
}

android {
  namespace = "org.sopt.linkmind"

  defaultConfig {
    applicationId = "org.sopt.linkmind"
    versionCode = 1
    versionName = "1.0"
  }
}

dependencies {

  implementation(projects.feature.mainfeature)
  implementation(projects.data.linkminddata)
  implementation(projects.data.linkminddataLocal)
  implementation(projects.data.linkminddataRemote)
  implementation(projects.core.network)
  implementation(projects.core.ui)
  implementation(projects.core.common)
  implementation(projects.core.datastore)
  implementation(projects.core.model)
  implementation(projects.core.auth)
  implementation(projects.core.authimpl)
  implementation(projects.domain.linkminddomain)
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  implementation(libs.constraintlayout)
  implementation(libs.timber)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
}
