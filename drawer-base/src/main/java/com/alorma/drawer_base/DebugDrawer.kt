package com.alorma.drawer_base

import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Possible values of [DebugDrawerState].
 */
enum class DebugDrawerValue {
    /**
     * The state of the drawer when it is closed.
     */
    Closed,

    /**
     * The state of the drawer when it is open.
     */
    Open
}

/**
 * State of the [ModalDrawerLayout] composable.
 *
 * @param initialValueDebug The initial value of the state.
 * @param clock The animation clock that will be used to drive the animations.
 * @param confirmStateChange Optional callback invoked to confirm or veto a pending state change.
 */
@Suppress("NotCloseable")
@OptIn(ExperimentalMaterialApi::class)
@Stable
class DebugDrawerState(
    initialValue: DebugDrawerValue,
    confirmStateChange: (DebugDrawerValue) -> Boolean = { true },
) : SwipeableState<DebugDrawerValue>(
    initialValue = initialValue,
    animationSpec = AnimationSpec,
    confirmStateChange = confirmStateChange
) {
    /**
     * Whether the drawer is open.
     */
    val isOpen: Boolean
        get() = currentValue == DebugDrawerValue.Open

    /**
     * Whether the drawer is closed.
     */
    val isClosed: Boolean
        get() = currentValue == DebugDrawerValue.Closed

    /**
     * Open the drawer with animation and suspend until it if fully opened or animation has been
     * cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the open animation ended
     */
    suspend fun open() = animateTo(DebugDrawerValue.Open)

    /**
     * Close the drawer with animation and suspend until it if fully closed or animation has been
     * cancelled. This method will throw [CancellationException] if the animation is
     * interrupted
     *
     * @return the reason the close animation ended
     */
    suspend fun close() = animateTo(DebugDrawerValue.Closed)

    companion object {
        /**
         * The default [Saver] implementation for [DrawerState].
         */
        fun Saver(confirmStateChange: (DebugDrawerValue) -> Boolean) =
            Saver<DebugDrawerState, DebugDrawerValue>(
                save = { it.currentValue },
                restore = { DebugDrawerState(it, confirmStateChange) }
            )
    }
}

/**
 * Create and [remember] a [DebugDrawerState] with the default animation clock.
 *
 * @param initialValueDebug The initial value of the state.
 * @param confirmStateChange Optional callback invoked to confirm or veto a pending state change.
 */
@Composable
fun rememberDebugDrawerState(
    initialValueDebug: DebugDrawerValue,
    confirmStateChange: (DebugDrawerValue) -> Boolean = { true },
): DebugDrawerState {
    return rememberSaveable(saver = DebugDrawerState.Saver(confirmStateChange)) {
        DebugDrawerState(initialValueDebug, confirmStateChange)
    }
}

/**
 * Navigation drawers provide access to destinations in your app.
 *
 * Modal navigation drawers block interaction with the rest of an app’s content with a scrim.
 * They are elevated above most of the app’s UI and don’t affect the screen’s layout grid.
 *
 * See [BottomDrawerLayout] for a layout that introduces a bottom drawer, suitable when
 * using bottom navigation.
 *
 * @sample androidx.compose.material.samples.ModalDrawerSample
 *
 * @param drawerContent composable that represents content inside the drawer
 * @param modifier optional modifier for the drawer
 * @param debugDrawerState state of the drawer
 * @param gesturesEnabled whether or not drawer can be interacted by gestures
 * @param drawerShape shape of the drawer sheet
 * @param drawerElevation drawer sheet elevation. This controls the size of the shadow below the
 * drawer sheet
 * @param drawerBackgroundColor background color to be used for the drawer sheet
 * @param drawerContentColor color of the content to use inside the drawer sheet. Defaults to
 * either the matching `onFoo` color for [drawerBackgroundColor], or, if it is not a color from
 * the theme, this will keep the same value set above this Surface.
 * @param scrimColor color of the scrim that obscures content when the drawer is open
 * @param bodyContent content of the rest of the UI
 *
 * @throws IllegalStateException when parent has [Float.POSITIVE_INFINITY] width
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun DebugDrawerLayout(
    modifier: Modifier = Modifier,
    isDebug: () -> Boolean = { false },
    drawerColors: Colors = debugDrawerColorsPalette,
    debugDrawerState: DebugDrawerState = rememberDebugDrawerState(DebugDrawerValue.Closed),
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerModules: @Composable ColumnScope.() -> Unit = { },
    bodyContent: @Composable () -> Unit,
) {

    if (!isDebug()) {
        bodyContent()
        return
    }

    val scope = rememberCoroutineScope()
    BoxWithConstraints(modifier.fillMaxSize()) {
        if (!constraints.hasBoundedWidth) {
            throw IllegalStateException("Drawer shouldn't have infinite width")
        }

        val minValue = constraints.maxWidth.toFloat()
        val maxValue = 0f

        val anchors = mapOf(minValue to DebugDrawerValue.Closed, maxValue to DebugDrawerValue.Open)
        val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

        Box(
            Modifier.swipeable(
                state = debugDrawerState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal,
                enabled = true,
                reverseDirection = isRtl,
                velocityThreshold = DrawerVelocityThreshold,
                resistance = null
            )
        ) {
            Box {
                bodyContent()
            }
            MaterialTheme(
                colors = drawerColors,
                shapes = MaterialTheme.shapes,
                typography = MaterialTheme.typography
            ) {
                Scrim(
                    open = debugDrawerState.isOpen,
                    onClose = { scope.launch { debugDrawerState.close() } },
                    fraction = {
                        calculateFraction(
                            minValue,
                            maxValue,
                            debugDrawerState.offset.value
                        )
                    },
                    color = DrawerDefaults.scrimColor
                )
                Surface(
                    color = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onSurface,
                    modifier = with(LocalDensity.current) {
                        Modifier
                            .width(this@BoxWithConstraints.constraints.maxWidth.toDp())
                            .height(this@BoxWithConstraints.constraints.maxHeight.toDp())
                            .padding(start = StartDrawerPadding)
                    }
                        .semantics {
                            if (debugDrawerState.isOpen) {
                                dismiss(action = { scope.launch { debugDrawerState.close() }; true })
                            }
                        }
                        .offset { IntOffset(debugDrawerState.offset.value.roundToInt(), 0) },
                    shape = drawerShape,
                    elevation = drawerElevation
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        drawerModules()
                    }
                }
            }
        }
    }
}

/**
 * Object to hold default values for [ModalDrawerLayout] and [BottomDrawerLayout]
 */
object DrawerDefaults {

    /**
     * Default Elevation for drawer sheet as specified in material specs
     */
    val Elevation = 16.dp

    val scrimColor: Color
        @Composable
        get() = MaterialTheme.colors.surface.copy(alpha = ScrimOpacity)

    /**
     * Default alpha for scrim color
     */
    const val ScrimOpacity = 0.32f
}

private fun calculateFraction(a: Float, b: Float, pos: Float) =
    ((pos - a) / (b - a)).coerceIn(0f, 1f)

@Composable
private fun Scrim(
    open: Boolean,
    onClose: () -> Unit,
    fraction: () -> Float,
    color: Color,
) {
    val dismissDrawer = if (open) {
        Modifier.pointerInput(Unit) { detectTapGestures(onTap = { onClose() }) }
    } else {
        Modifier
    }

    Canvas(
        Modifier
            .fillMaxSize()
            .then(dismissDrawer)
    ) {
        drawRect(color, alpha = fraction())
    }
}

private val StartDrawerPadding = 56.dp
private val DrawerVelocityThreshold = 400.dp

private val AnimationSpec = TweenSpec<Float>(durationMillis = 100)