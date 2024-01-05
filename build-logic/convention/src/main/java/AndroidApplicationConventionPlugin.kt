import com.android.build.api.dsl.ApplicationExtension
import com.linkmind.convention.Const
import com.linkmind.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.android")
      }

      extensions.configure<ApplicationExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = Const.targetSdk

      }
      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      dependencies {
        "implementation"(libs.findLibrary("navigation.fragment.ktx").get())
        "implementation"(libs.findLibrary("navigation.ui.ktx").get())
      }
    }
  }
}
