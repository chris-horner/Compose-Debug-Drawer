package com.alorma.drawer_base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp

@Composable
fun DebugDrawerModule(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    title: String,
    contentModifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val expandedState: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    val semanticsModifier = Modifier.semantics {
        testTag = "Module $title"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (expandedState.value) modifier else Modifier)
            .then(semanticsModifier),
    ) {
        DrawerModuleHeader(
            icon = icon,
            title = title,
            expandedState = expandedState,
        )

        val contentSemanticsModifier = Modifier.semantics {
            testTag = "Module content $title"
        }
        if (expandedState.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .then(contentModifier)
                    .then(contentSemanticsModifier),
            ) {
                content()
            }
        }
    }
}