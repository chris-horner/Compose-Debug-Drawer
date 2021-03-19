package com.alorma.drawer_ui_modules.design

import androidx.compose.ui.graphics.Color

data class DebugGridStateConfig internal constructor(
    val isEnabled: Boolean,
    val color: Color,
    val alpha: Float,
) {

    companion object {
        operator fun invoke(
            isEnabled: Boolean = false,
            color: Color = Color.Red,
            alpha: Float = 0.3f,
        ) = DebugGridStateConfig(
            isEnabled = isEnabled,
            color = color,
            alpha = alpha,
        )
    }
}
