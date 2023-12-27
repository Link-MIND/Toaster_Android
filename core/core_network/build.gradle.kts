@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.linkmind.android.hilt)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "org.sopt.core.core_network"
}

dependencies {
  implementation(projects.core.coreModel)
  implementation(libs.bundles.coroutine)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.retrofit.core)
  implementation(libs.retrofit.kotlin.serialization)
  implementation(libs.okhttp.logging)
  implementation(libs.timber)
}
