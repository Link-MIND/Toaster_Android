// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.ktlint)
  alias(libs.plugins.detekt)
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.hilt) apply false
  alias(libs.plugins.google.service) apply false
  alias(libs.plugins.navigation.safeargs) apply false
}
allprojects {
  apply {
    plugin(rootProject.libs.plugins.detekt.get().pluginId)
    plugin(rootProject.libs.plugins.ktlint.get().pluginId)
  }
  afterEvaluate {
    detekt {
      parallel = true
      buildUponDefaultConfig = true
      toolVersion = libs.versions.detekt.get()
      config.setFrom(files("$rootDir/detekt-config.yml"))
    }
  }
}
buildscript {
  repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
  }
}
