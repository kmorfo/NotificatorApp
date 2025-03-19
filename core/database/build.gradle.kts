plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
    alias(libs.plugins.notificatorapp.multiplatform.room)
    alias(libs.plugins.notificatorapp.multiplatform.kotlin.test)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.onboarding.domain)

            implementation(libs.kotlinx.datetime)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
android {
    namespace = "es.rlujancreations.core.database"
}
dependencies {
    add("kspDesktop", libs.androidx.room.compiler)
}
