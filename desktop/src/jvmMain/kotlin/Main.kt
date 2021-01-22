package com.alorma.composedrawer

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugDrawerLayout
import com.alorma.drawer_base.DebugDrawerState
import com.alorma.drawer_base.DebugDrawerValue
import com.alorma.drawer_base.rememberDebugDrawerState
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.DropdownAction
import com.alorma.drawer_modules.actions.TextAction
import java.time.LocalDate

fun main() = Window {
    val debugDrawerState = rememberDebugDrawerState(initialValueDebug = DebugDrawerValue.Open)
    DebugDrawerLayout(
        isDebug = { true },
        debugDrawerState = debugDrawerState,
        drawerModules = {
            val modulesModifier = Modifier
                .padding(4.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colors.surface)
            DemoActionsModule(modulesModifier)
        }
    ) { AppContent(debugDrawerState) }
}

@Composable
private fun AppContent(debugDrawerState: DebugDrawerState) {
    Scaffold(
        topBar = {
            val title = "Drawer Desktop"
            TopAppBar(
                elevation = 0.dp,
                title = { Text(text = title) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            drawerButton(debugDrawerState)
        }

    }
}

@Composable
private fun drawerButton(debugDrawerState: DebugDrawerState) {
    Button(
        onClick = {
            if (debugDrawerState.isOpen) {
                debugDrawerState.close()
            } else if (debugDrawerState.isClosed) {
                debugDrawerState.open()
            }
        }) {
        if (debugDrawerState.isOpen) {
            Text(text = "Close DRAWER")
        } else {
            Text(text = "Open DRAWER")
        }
    }
}
