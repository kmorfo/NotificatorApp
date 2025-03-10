plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "es.rlujancreations.core.domain"
}
