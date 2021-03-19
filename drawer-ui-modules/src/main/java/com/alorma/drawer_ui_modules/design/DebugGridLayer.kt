package com.alorma.drawer_ui_modules.design

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DebugGridLayer(
    debugGridLayerConfig: DebugGridStateConfig,
) {
    CompositionLocalProvider(LocalDebugGridConfig provides debugGridLayerConfig) {
        if (LocalDebugGridConfig.current.isEnabled) {
            val color = Color.Red.copy(alpha = LocalDebugGridConfig.current.alpha)
            DebugGridLayerCanvas(color = color)
        }
    }
}

@Composable
internal fun DebugGridLayerCanvas(
    color: Color,
    gridSize: Dp = 8.dp,
) {
    Canvas(Modifier.fillMaxSize()) {
        val offset = gridSize.toPx()
        val lineWidth = 1f
        var x = 0f
        while (x < size.width) {
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = lineWidth,
                color = color,
            )
            x += offset
        }
        var y = 0f
        while (y < size.height) {
            drawLine(
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = lineWidth,
                color = color,
            )
            y += offset
        }
    }
}
