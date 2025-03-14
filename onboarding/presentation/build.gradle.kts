plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library.compose)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.components.resources)

            implementation(projects.core.domain)
        }
    }
}

android {
    namespace = "es.rlujancreations.onboarding.presentation"
}
