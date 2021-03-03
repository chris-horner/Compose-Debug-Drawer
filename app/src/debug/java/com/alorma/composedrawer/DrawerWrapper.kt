package com.alorma.composedrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alorma.composedrawer.modules.DemoActionsModule
import com.alorma.drawer_base.DebugDrawerLayout
import com.alorma.drawer_modules.BuildModule
import com.alorma.drawer_modules.DeviceModule

@Composable
fun ConfigureScreen(bodyContent: @Composable (isDrawerOpen: Boolean) -> Unit) {
    DebugDrawerLayout(
        drawerModules = {
            val modulesModifier = Modifier
                .padding(4.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colors.surface)
            DemoActionsModule(modulesModifier)
            BuildModule(modulesModifier)
            DeviceModule(modulesModifier)
        },
        bodyContent = { drawerState -> bodyContent(drawerState.isOpen) },
    )
}