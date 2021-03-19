package com.alorma.drawer_ui_modules.design

sealed class DebugGridStateConfig {
    object Disabled: DebugGridStateConfig()
    object Enabled: DebugGridStateConfig()
}
