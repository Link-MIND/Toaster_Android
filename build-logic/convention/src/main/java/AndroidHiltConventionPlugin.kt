import com.linkmind.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.google.devtools.ksp")
        apply("dagger.hilt.android.plugin")
      }

      dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "ksp"(libs.findLibrary("hilt.compiler").get())
        "testImplementation"(libs.findLibrary("hilt.testing").get())
        "kspTest"(libs.findLibrary("hilt.testing.compiler").get())
      }
    }
  }
}
