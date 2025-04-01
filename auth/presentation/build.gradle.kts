plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library.compose)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.components.resources)

            implementation(projects.auth.domain)

            implementation(projects.core.domain)
            implementation(projects.core.presentation)
        }
    }
}

android {
    namespace = "es.rlujancreations.auth.presentation"
}
