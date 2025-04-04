package es.rlujancreations.core.presentation

import java.text.MessageFormat
import java.util.Properties

actual class GetStringProvider : StringProvider {
    private val properties = Properties()

    init {
        loadStringsProperties()
    }

    private fun loadStringsProperties() {
        try {
            val stringsPropertiesPath = "/strings.properties"

            var loaded = false

            javaClass.getResourceAsStream(stringsPropertiesPath)?.use { inputStream ->
                properties.load(inputStream)
                loaded = true
            }

            if (!loaded) {
                println("We can't load strings.properties, loading from fallback")
                loadFallbackStrings()
            }
        } catch (e: Exception) {
            println("Error loading strings.properties: ${e.message}")
            e.printStackTrace()
            loadFallbackStrings()
        }
    }

    private fun loadFallbackStrings() {
        properties.setProperty("error_unknown", "An unknown error occurred.")
        println("Using fallback strings.properties")
    }

    override fun getString(
        resId: String,
        vararg args: Any,
    ): String {
        val template = properties.getProperty(resId) ?: return resId

        return if (args.isEmpty()) {
            template
        } else {
            try {
                MessageFormat.format(template, *args)
            } catch (e: Exception) {
                println("Error formatting string '$resId': ${e.message}")
                template
            }
        }
    }
}
