package es.rlujancreations.core.presentation

import java.io.FileInputStream
import java.text.MessageFormat
import java.util.Properties

actual class GetStringProvider(
    private val stringsPropertiesPath: String = "resources/strings.properties",
) : StringProvider {
    private val properties = Properties().apply {
        try {
            load(FileInputStream(stringsPropertiesPath))
        } catch (e: Exception) {
            println("Error loading string resources: ${e.message}")
        }
    }

    override fun getString(resId: String, vararg args: Any): String {
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

