@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.plugin.data)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "org.sopt.remote.category"
}

dependencies {
  implementation(projects.core.network)
  implementation(projects.data.category)
  implementation(libs.retrofit.kotlin.serialization)
}
