package es.rlujancreations.core.presentation

/**
 * Created by Raúl L.C. on 12/3/25.
 */
interface StringProvider {
    fun getString(
        resId: String,
        vararg args: Any,
    ): String
}

expect class GetStringProvider : StringProvider
