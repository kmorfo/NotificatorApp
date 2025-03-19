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
            implementation(projects.core.presentation)

            implementation(projects.onboarding.domain)
        }
    }
}

android {
    namespace = "es.rlujancreations.onboarding.presentation"
}
