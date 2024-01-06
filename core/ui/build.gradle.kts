@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.linkmind.android.hilt)
}

android {
  namespace = "org.sopt.ui"

  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.fragment.ktx)
  implementation(libs.androidx.recyclerview)
  implementation(libs.google.material)
  implementation(libs.timber)
  implementation(libs.bundles.androidx.lifecycle)
  implementation(libs.bundles.coroutine)
}
