plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.ktor)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(projects.auth.domain)

            implementation(projects.core.domain)
            implementation(projects.core.data)
            implementation(projects.core.database)
        }
    }
}
android {
    namespace = "es.rlujancreations.auth.data"
}
