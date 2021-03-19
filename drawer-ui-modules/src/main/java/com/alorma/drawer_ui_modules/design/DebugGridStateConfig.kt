package com.alorma.drawer_ui_modules.design

data class DebugGridStateConfig internal constructor(
    val isEnabled: Boolean,
) {
    companion object {
        operator fun invoke(
            isEnabled: Boolean = false,
        ) = DebugGridStateConfig(
            isEnabled = isEnabled
        )
    }
}
