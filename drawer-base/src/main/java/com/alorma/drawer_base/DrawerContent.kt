package com.alorma.drawer_base

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    initialModulesState: ModuleExpandedState = ModuleExpandedState.EXPANDED,
    drawerModules: @Composable ColumnScope.(Modifier, ModuleExpandedState) -> Unit = { _, _ -> },
) {
    ScrollableColumn(modifier = Modifier.fillMaxSize()) {
        drawerModules(modifier, initialModulesState)
    }
}