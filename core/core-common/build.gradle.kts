@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "org.sopt.core_common"
}

dependencies {

  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  implementation(libs.bundles.coroutine)
  implementation(libs.timber)
}
