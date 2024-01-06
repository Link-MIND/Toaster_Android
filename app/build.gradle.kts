@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.application)
  alias(libs.plugins.linkmind.android.hilt)
  alias(libs.plugins.google.service)
}

android {
  namespace = "org.sopt.linkmind"

  defaultConfig {
    applicationId = "org.sopt.linkmind"
    versionCode = 1
    versionName = "1.0"
  }
  viewBinding.enable = true
}

dependencies {
  implementation(projects.core.network)
  implementation(projects.core.ui)
  implementation(projects.core.common)
  implementation(projects.core.datastore)
  implementation(projects.core.model)
  implementation(projects.core.auth)
  implementation(projects.core.authimpl)
  implementation(projects.domain.linkminddomain)
  implementation(projects.domain.oauthdomain)
  implementation(projects.data.linkminddata)
  implementation(projects.data.oauthdata)
  implementation(projects.feature.designsystem)
  implementation(projects.feature.maincontainer)
  implementation(projects.feature.clip)
  implementation(projects.feature.mypage)
  implementation(projects.feature.timer)
  implementation(projects.feature.login)
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  implementation(libs.constraintlayout)
  implementation(libs.timber)
  implementation(libs.kakao.login)
  implementation(libs.bundles.firebase)
  implementation(platform(libs.firebase.bom))
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
}
