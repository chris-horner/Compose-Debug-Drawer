package com.alorma.drawer_modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.alorma.drawer_base.DebugDrawerModule
import com.alorma.drawer_base.IconType

@Composable
fun actionsModule(
    icon: IconType,
    title: String,
    actions: @Composable ColumnScope.() -> Unit
) {
    DebugDrawerModule(
        icon = icon,
        title = title
    ) {
        Column { actions() }
    }
}
