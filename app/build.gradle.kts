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
    versionCode = 6
    versionName = "1.0"
  }

  viewBinding.enable = true
  buildTypes {
    getByName("release") {
      signingConfig = signingConfigs.getByName("debug")
      isMinifyEnabled = false
      proguardFiles(
        "proguard-rules.pro",
      )
    }
  }
}

dependencies {
  implementation(projects.core.network)
  implementation(projects.core.ui)
  implementation(projects.core.common)
  implementation(projects.core.datastore)
  implementation(projects.core.model)
  implementation(projects.core.auth)
  implementation(projects.core.authimpl)
  implementation(projects.core.designsystem)
  implementation(projects.data.linkminddata)
  implementation(projects.data.oauthdata)
  implementation(projects.data.timer)
  implementation(projects.data.category)
  implementation(projects.data.user)
  implementation(projects.dataRemote.timer)
  implementation(projects.dataRemote.category)
  implementation(projects.dataRemote.user)
  implementation(projects.data.linkminddata)
  implementation(projects.data.oauthdata)
  implementation(projects.feature.maincontainer)
  implementation(projects.feature.clip)
  implementation(projects.feature.mypage)
  implementation(projects.feature.timer)
  implementation(projects.feature.login)
  implementation(libs.timber)
  implementation(libs.kakao.login)
  implementation(libs.bundles.firebase)
  implementation(platform(libs.firebase.bom))
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
}
