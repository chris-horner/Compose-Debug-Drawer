package com.alorma.drawer_ui_modules.design

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDebugGridConfig: ProvidableCompositionLocal<DebugGridStateConfig> =
    staticCompositionLocalOf { DebugGridStateConfig(false) }