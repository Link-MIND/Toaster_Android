@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "org.sopt.mainfeature"
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(projects.core.ui)
  implementation(projects.core.common)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.recyclerview)
  implementation(libs.google.material)
  implementation(libs.coil)
  implementation(libs.timber)
}
