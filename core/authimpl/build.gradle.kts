@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.linkmind.android.hilt)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "org.sopt.authimpl"
}

dependencies {
  implementation(projects.core.network)
  implementation(projects.core.auth)
  implementation(projects.core.datastore)
  implementation(libs.bundles.coroutine)
  implementation(libs.retrofit.kotlin.serialization)
}
