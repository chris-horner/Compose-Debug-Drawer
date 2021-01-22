package com.alorma.composedrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugDrawerLayout

@Composable
fun HomeScreen() {
    DebugDrawerLayout(
        isDebug = { true },
        initialDrawerState = DrawerValue.Open,
        drawerModules = {
            val modulesModifier = Modifier
                .padding(4.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colors.surface)
        }
    ) { drawerState -> AppContent(drawerState) }
}

@Composable
private fun AppContent(drawerState: DrawerState) {
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
            drawerButton(drawerState)
        }

    }
}

@Composable
private fun drawerButton(drawerState: DrawerState) {
    Button(
        onClick = {
            if (drawerState.isOpen) {
                drawerState.close()
            } else if (drawerState.isClosed) {
                drawerState.open()
            }
        }) {
        if (drawerState.isOpen) {
            Text(text = "Close DRAWER")
        } else {
            Text(text = "Open DRAWER")
        }
    }
}
