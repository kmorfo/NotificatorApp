plugins {
    alias(libs.plugins.notificatorapp.multiplatform.library)
    alias(libs.plugins.notificatorapp.multiplatform.room)
    alias(libs.plugins.notificatorapp.multiplatform.koin)
    alias(libs.plugins.notificatorapp.multiplatform.kotlin.test)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            // Import modules to test
            implementation(projects.core.database)

            implementation(libs.kotlinx.datetime)

            implementation(libs.kotlinx.serialization.json)

            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(projects.commonTest)
        }
    }
}

android {
    namespace = "es.rlujancreations.commonTest"
}
