@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.linkmind.android.hilt)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "org.sopt.datastore"
}

dependencies {

  implementation(libs.bundles.coroutine)
  implementation(libs.bundles.datastore)
  ksp(libs.encrypted.datastore.preference.ksp)
  implementation(libs.bundles.encrypted.datastore)
}
