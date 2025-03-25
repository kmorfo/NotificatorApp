plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library.compose)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
        }
        commonMain.dependencies {
            implementation(compose.components.resources)

            implementation(projects.core.domain)
            implementation(projects.core.presentation)

            implementation(projects.onboarding.domain)

            //Problems with IOS, solved temporary with my own implementation to get screen dimensions
//            implementation(libs.compose.material3.adaptive.layout)
        }
    }
}

android {
    namespace = "es.rlujancreations.onboarding.presentation"
}
