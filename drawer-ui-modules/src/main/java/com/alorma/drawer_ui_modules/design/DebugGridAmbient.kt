package com.alorma.drawer_ui_modules.design

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val DebugGridConfig: ProvidableCompositionLocal<DebugGridStateConfig> = staticCompositionLocalOf {
    DebugGridStateConfig.Disabled
}