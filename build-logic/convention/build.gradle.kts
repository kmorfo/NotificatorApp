@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

group = "es.rlujancreations.notificatorapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}
gradlePlugin {
    plugins {
        register("multiplatformApplication") {
            id = "notificatorapp.multiplatform.application"
            implementationClass = "KmpApplicationConventionPlugin"
        }
        register("multiplatformApplicationCompose") {
            id = "notificatorapp.multiplatform.application.compose"
            implementationClass = "KmpApplicationComposeConventionPlugin"
        }
        register("multiplatformLibrary") {
            id = "notificatorapp.multiplatform.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("multiplatformLibraryCompose") {
            id = "notificatorapp.multiplatform.library.compose"
            implementationClass = "KmpLibraryComposeConventionPlugin"
        }
        register("multiplatformFeatureUi") {
            id = "notificatorapp.multiplatform.feature.ui"
            implementationClass = "KmpFeatureUiConventionPlugin"
        }
        register("kmpRoom") {
            id = "notificatorapp.multiplatform.room"
            implementationClass = "KmpRoomConventionPlugin"
        }
        register("KmpKoin") {
            id = "notificatorapp.multiplatform.koin"
            implementationClass = "KmpKoinConventionPlugin"
        }
        register("KmpKtor") {
            id = "notificatorapp.multiplatform.ktor"
            implementationClass = "KmpKtorConventionPlugin"
        }
        register("KmpKotlinTest") {
            id = "notificatorapp.multiplatform.kotlin.test"
            implementationClass = "KmpKotlinTestConventionPlugin"
        }
    }
}
