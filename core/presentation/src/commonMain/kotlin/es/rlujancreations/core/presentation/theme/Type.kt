package es.rlujancreations.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.montserrat_regular
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() =
    FontFamily(
        Font(
            resource = Res.font.montserrat_regular,
            weight = FontWeight.Medium,
        ),
    )

val baseline = Typography()

@Composable
fun MontserratTypography() =
    Typography().run {
        val fontFamily = MontserratFontFamily()
        copy(
            displayLarge = baseline.displayLarge.copy(fontFamily = MontserratFontFamily()),
            displayMedium = baseline.displayMedium.copy(fontFamily = MontserratFontFamily()),
            displaySmall = baseline.displaySmall.copy(fontFamily = MontserratFontFamily()),
            headlineLarge = baseline.headlineLarge.copy(fontFamily = MontserratFontFamily()),
            headlineMedium = baseline.headlineMedium.copy(fontFamily = MontserratFontFamily()),
            headlineSmall = baseline.headlineSmall.copy(fontFamily = MontserratFontFamily()),
            titleLarge = baseline.titleLarge.copy(fontFamily = MontserratFontFamily()),
            titleMedium = baseline.titleMedium.copy(fontFamily = MontserratFontFamily()),
            titleSmall = baseline.titleSmall.copy(fontFamily = MontserratFontFamily()),
        )
    }

