import org.jetbrains.compose.desktop.application.dsl.TargetFormat


plugins {
    alias(libs.plugins.notificatorapp.multiplatform.application.compose)
}

kotlin {
    jvm("desktop")
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies { }
        commonMain.dependencies {
            implementation(libs.napier)

            implementation(projects.core.presentation)
            implementation(projects.core.data)
            implementation(projects.core.domain)
            implementation(projects.core.database)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "es.rlujancreations.notificatorapp"
}

compose.desktop {
    application {
        mainClass = "es.rlujancreations.notificatorapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "es.rlujancreations.notificatorapp"
            packageVersion = "1.0.0"
        }
    }
}
