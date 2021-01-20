package com.alorma.drawer_base

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DrawerContent(
    drawerModules: @Composable ColumnScope.() -> Unit = { },
) {
    ScrollableColumn(modifier = Modifier.fillMaxSize()) {
        drawerModules()
    }
}