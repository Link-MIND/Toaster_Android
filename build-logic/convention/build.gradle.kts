plugins {
  `kotlin-dsl`
}

group = "com.linkmind.convention"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  compileOnly(libs.android.gradle.plugin)
  compileOnly(libs.kotlin.gradle.plugin)
  compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = "linkmind.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidLibrary") {
      id = "linkmind.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidHilt") {
      id = "linkmind.android.hilt"
      implementationClass = "AndroidHiltConventionPlugin"
    }
    register("javaLibrary") {
      id = "linkmind.java.library"
      implementationClass = "JavaLibraryConventionPlugin"
    }
    register("FeaturePlugin") {
      id = "linkmind.plugin.feature"
      implementationClass = "FeatureConventionPlugin"
    }
  }
}
