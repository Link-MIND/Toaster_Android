@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.linkmind.plugin.feature)
  alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.share"
}

dependencies {
  implementation(projects.core.datastore)
  implementation(projects.domain.link)
  implementation(projects.domain.category)
  implementation(libs.androidx.window)
  implementation(libs.process.phoenix)
}

