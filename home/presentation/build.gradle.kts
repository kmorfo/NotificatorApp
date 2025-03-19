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

            // Adaptive components
            implementation(libs.compose.material3.adaptive)
            implementation(libs.compose.material3.adaptive.layout)
            implementation(libs.compose.material3.adaptive.nav.suite)
            implementation(libs.compose.material3.adaptive.navigation)
        }
    }
}

android {
    namespace = "es.rlujancreations.home.presentation"
}
