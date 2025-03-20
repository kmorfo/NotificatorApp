package es.rlujancreations.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.montserrat_regular
import notificatorapp.core.presentation.generated.resources.opensans_medium
import notificatorapp.core.presentation.generated.resources.opensans_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() =
    FontFamily(
        Font(
            resource = Res.font.montserrat_regular,
            weight = FontWeight.Medium,
        ),
    )

@Composable
fun OpenSansFontFamily() =
    FontFamily(
        Font(
            resource = Res.font.opensans_medium,
            weight = FontWeight.Medium,
        ),
        Font(
            resource = Res.font.opensans_semibold,
            weight = FontWeight.SemiBold,
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

@Composable
fun OpenSansTypography() =
    Typography().run {
        val fontFamily = OpenSansFontFamily()
        copy(
            displayLarge = baseline.displayLarge.copy(fontFamily = OpenSansFontFamily()),
            displayMedium = baseline.displayMedium.copy(fontFamily = OpenSansFontFamily()),
            displaySmall = baseline.displaySmall.copy(fontFamily = OpenSansFontFamily()),
            headlineLarge = baseline.headlineLarge.copy(fontFamily = OpenSansFontFamily()),
            headlineMedium = baseline.headlineMedium.copy(fontFamily = OpenSansFontFamily()),
            headlineSmall = baseline.headlineSmall.copy(fontFamily = OpenSansFontFamily()),
            titleLarge = baseline.titleLarge.copy(fontFamily = OpenSansFontFamily()),
            titleMedium = baseline.titleMedium.copy(fontFamily = OpenSansFontFamily()),
            titleSmall = baseline.titleSmall.copy(fontFamily = OpenSansFontFamily()),
        )
    }
