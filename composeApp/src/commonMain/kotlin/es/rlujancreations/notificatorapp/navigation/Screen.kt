package es.rlujancreations.notificatorapp.navigation

import kotlinx.serialization.Serializable

/**
 * Created by Raúl L.C. on 19/1/25.
 */

@Serializable
sealed class Screen {

    @Serializable
    object OnBoarding {
        @Serializable
        object Intro
    }

    @Serializable
    object Auth {
        @Serializable
        object Register

        @Serializable
        object Login

        @Serializable
        object Recovery
    }

    @Serializable
    object Home {
        @Serializable
        object Scaffold

        @Serializable
        object Settings
    }
}
