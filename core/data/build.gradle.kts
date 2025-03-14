import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import java.util.Properties

plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.ktor)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
    alias(libs.plugins.gradleBuildConfig)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.androidx.security.crypto)
            implementation(libs.androidx.startup.runtime)
        }
        commonMain.dependencies {
            implementation(projects.core.domain)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

buildConfig {
    packageName("es.rlujancreations.core.data")
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").reader())
    val baseUrl = properties.getProperty("BASE_URL")

    buildConfigField("BASE_URL", baseUrl)
}

android {
    namespace = "es.rlujancreations.core.data"
}
