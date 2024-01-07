@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.linkmind.plugin.feature)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.savelink"
}

dependencies {
  implementation(projects.core.designsystem)
}
