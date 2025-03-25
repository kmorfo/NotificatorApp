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
            implementation(libs.androidx.navigation.compose)

            implementation(projects.core.data)
            implementation(projects.core.domain)
            implementation(projects.core.database)
            implementation(projects.core.presentation)

            implementation(projects.onboarding.domain)
            implementation(projects.onboarding.presentation)

            implementation(projects.auth.data)
            implementation(projects.auth.domain)
            implementation(projects.auth.presentation)

            implementation(projects.home.data)
            implementation(projects.home.domain)
            implementation(projects.home.presentation)
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

            macOS {
                iconFile.set(project.file("resources/icon.icns"))
            }
//            windows {
//                iconFile.set(project.file("resources/icon.ico"))
//            }
//            linux {
//                iconFile.set(project.file("resources/icon.icon"))
//            }
        }
    }
}
