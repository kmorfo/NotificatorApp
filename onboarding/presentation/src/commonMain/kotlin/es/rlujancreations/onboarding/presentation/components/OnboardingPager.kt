package es.rlujancreations.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.core.presentation.Shapes
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.getScreenDimensions
import es.rlujancreations.onboarding.presentation.OnboardingPagerInformation
import kotlinx.coroutines.launch
import notificatorapp.onboarding.presentation.generated.resources.Res
import notificatorapp.onboarding.presentation.generated.resources.btn_getStarted
import notificatorapp.onboarding.presentation.generated.resources.btn_next
import notificatorapp.onboarding.presentation.generated.resources.btn_skip
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.absoluteValue

/**
 * Created by Raúl L.C. on 18/3/25.
 */
@Composable
fun OnBoardingPager(
    pages: List<OnboardingPagerInformation>,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
) {
    val windowClass = getScreenDimensions().windowWidthSizeClass

    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { index ->
        Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
            val information = pages[index]

            if (windowClass != WindowWidthSizeClass.Expanded) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    ContentOnboarding(windowClass, information)
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ContentOnboarding(windowClass, information)
                }
            }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (pagerState.currentPage == pages.lastIndex) {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                        onClick = onFinish,
                    ) {
                        Text(
                            stringResource(Res.string.btn_getStarted),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                } else {
                    OutlinedButton(onClick = onFinish) {
                        Text(
                            text = stringResource(Res.string.btn_skip),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    }
                    HorizontalPagerIndicator(
                        pageCount = pages.size,
                        currentPage = pagerState.currentPage,
                        targetPage = pagerState.targetPage,
                        currentPageOffsetFraction = pagerState.currentPageOffsetFraction,
                        activeColor = MaterialTheme.colorScheme.tertiary,
                        inactiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(Res.string.btn_next),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentOnboarding(
    windowClass: WindowWidthSizeClass,
    information: OnboardingPagerInformation,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth(
                    if (windowClass == WindowWidthSizeClass.Expanded) 0.5f else 1f,
                )
                .background(
                    if (windowClass == WindowWidthSizeClass.Expanded) {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    },
                    shape = Shapes.medium,
                )
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = Shapes.medium)
                .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            stringResource(information.title),
            style = MaterialTheme.typography.headlineMedium,
            color =
                if (windowClass == WindowWidthSizeClass.Expanded) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.background
                },
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            stringResource(information.subtitle),
            style = MaterialTheme.typography.titleMedium,
            color =
                if (windowClass == WindowWidthSizeClass.Expanded) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.background
                },
            textAlign = TextAlign.Justify,
        )
    }
    Spacer(modifier = Modifier.size(24.dp))
    Box(
        modifier =
            Modifier.fillMaxHeight().background(
                if (windowClass == WindowWidthSizeClass.Expanded) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.background
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(information.image),
            contentDescription = "Onboarding " + stringResource(information.title),
            contentScale = ContentScale.Fit,
            modifier = Modifier.aspectRatio(1f).clip(Shapes.medium),
        )
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.DarkGray,
    inactiveColor: Color = activeColor.copy(alpha = 0.1f),
    unselectedIndicatorSize: Dp = 10.dp,
    selectedIndicatorSize: Dp = 12.dp,
    indicatorCornerRadius: Dp = 2.dp,
    indicatorPadding: Dp = 2.dp,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .wrapContentSize()
                .height(selectedIndicatorSize + indicatorPadding * 2),
    ) {
        repeat(pageCount) { page ->
            val (color, size) =
                if (currentPage == page || targetPage == page) {
                    val pageOffset =
                        ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                    // calculate offset percentage between 0.0 and 1.0
                    val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                    val size =
                        unselectedIndicatorSize + (
                            (selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage
                        )

                    activeColor.copy(alpha = offsetPercentage) to size
                } else {
                    inactiveColor to unselectedIndicatorSize
                }

            // draw indicator
            Box(
                modifier =
                    Modifier
                        .padding(
                            vertical = size / 4,
                            horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2,
                        )
                        .clip(RoundedCornerShape(indicatorCornerRadius))
                        .background(color)
                        .width(size)
                        .height(size / 2),
            )
        }
    }
}
