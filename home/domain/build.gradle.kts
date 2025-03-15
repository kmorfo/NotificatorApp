plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(projects.core.domain)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "es.rlujancreations.home.domain"
}
