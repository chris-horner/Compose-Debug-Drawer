package com.alorma.drawer_ui_modules.design

data class DebugGridStateConfig internal constructor(
    val isEnabled: Boolean,
    val alpha: Float,
) {

    companion object {
        operator fun invoke(
            isEnabled: Boolean = false,
            alpha: Float = 0.3f,
        ) = DebugGridStateConfig(
            isEnabled = isEnabled,
            alpha = alpha,
        )
    }
}
