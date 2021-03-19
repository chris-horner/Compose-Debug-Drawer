package com.alorma.composedrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alorma.composedrawer.modules.DemoActionsModule
import com.alorma.drawer_base.DebugDrawerLayout
import com.alorma.drawer_modules.BuildModule
import com.alorma.drawer_modules.DeviceModule
import com.alorma.drawer_ui_modules.design.DebugGridLayer
import com.alorma.drawer_ui_modules.design.DebugGridStateConfig
import com.alorma.drawer_ui_modules.design.DesignModule
import com.alorma.drawer_ui_modules.design.LocalDebugGridConfig

@Composable
fun ConfigureScreen(bodyContent: @Composable (isDrawerOpen: Boolean) -> Unit) {

    var debugGridLayerConfig: DebugGridStateConfig by remember {
        mutableStateOf(DebugGridStateConfig())
    }

    DebugDrawerLayout(
        drawerModules = {
            val modulesModifier = Modifier
                .padding(4.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colors.surface)
            DesignModule(modulesModifier, config = debugGridLayerConfig) {
                debugGridLayerConfig = it
            }
            DemoActionsModule(modulesModifier)
            BuildModule(modulesModifier)
            DeviceModule(modulesModifier)
        },
        bodyContent = { drawerState ->
            Box {
                bodyContent(drawerState.isOpen)
                CompositionLocalProvider(LocalDebugGridConfig provides debugGridLayerConfig) {
                    DebugGridLayer()
                }
            }
        },
    )
}