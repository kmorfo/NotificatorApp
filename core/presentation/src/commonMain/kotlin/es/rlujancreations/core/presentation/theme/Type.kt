package es.rlujancreations.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.productsans_bold
import notificatorapp.core.presentation.generated.resources.productsans_medium
import notificatorapp.core.presentation.generated.resources.productsans_regular
import org.jetbrains.compose.resources.Font

@Composable
fun FirebaseFontFamily() =
    FontFamily(
        Font(
            resource = Res.font.productsans_regular,
            weight = FontWeight.Normal,
        ),
        Font(
            resource = Res.font.productsans_medium,
            weight = FontWeight.Medium,
        ),
        Font(
            resource = Res.font.productsans_bold,
            weight = FontWeight.Bold,
        ),
    )

val baseline = Typography()

@Composable
fun FirebaseTypography() =
    Typography().run {
        copy(
            // Display styles
            displayLarge =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 57.sp,
                    letterSpacing = (-0.25).sp,
                ),
            displayMedium =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    letterSpacing = 0.sp,
                ),
            displaySmall =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    letterSpacing = 0.sp,
                ),
            // Headline styles
            headlineLarge =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    letterSpacing = 0.sp,
                ),
            headlineMedium =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp,
                    letterSpacing = 0.sp,
                ),
            headlineSmall =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    letterSpacing = 0.sp,
                ),
            // Estilos Title
            titleLarge =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    letterSpacing = 0.sp,
                ),
            titleMedium =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp,
                ),
            titleSmall =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    letterSpacing = 0.1.sp,
                ),
            // Body styles
            bodyLarge =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp,
                ),
            bodyMedium =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp,
                ),
            bodySmall =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 0.4.sp,
                ),
            // Label styles
            labelLarge =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    letterSpacing = 0.1.sp,
                ),
            labelMedium =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp,
                ),
            labelSmall =
                TextStyle(
                    fontFamily = FirebaseFontFamily(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    letterSpacing = 0.5.sp,
                ),
        )
    }
