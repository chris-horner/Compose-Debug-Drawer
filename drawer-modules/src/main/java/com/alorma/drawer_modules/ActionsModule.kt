package com.alorma.drawer_modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.drawer_base.DebugDrawerModule

@Composable
fun ActionsModule(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    title: String,
    actions: @Composable (ColumnScope.() -> Unit)
) {
    DebugDrawerModule(
        modifier = modifier,
        icon = icon,
        title = title
    ) {
        Column { actions() }
    }
}
