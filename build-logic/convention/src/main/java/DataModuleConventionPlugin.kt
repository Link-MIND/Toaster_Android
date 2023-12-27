
import com.linkmind.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class DataModuleConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("linkmind.android.library")
        apply("linkmind.android.hilt")
      }

      dependencies {
        "implementation"(project(":core:core-model"))
        "implementation"(libs.findBundle("coroutine").get())
        "implementation"(libs.findLibrary("timber").get())
      }
    }
  }
}
