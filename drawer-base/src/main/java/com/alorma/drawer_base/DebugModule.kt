package com.alorma.drawer_base

import androidx.annotation.DrawableRes
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
    icon: IconType,
    title: String,
    contentModifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val expandedState: MutableState<ModuleExpandedState> = remember {
        mutableStateOf(ModuleExpandedState.EXPANDED)
    }

    val semanticsModifier = Modifier.semantics {
        testTag = "Module $title"
    }

    Column(
        modifier = semanticsModifier
            .then(Modifier.fillMaxWidth())
            .then(modifier),
    ) {
        DrawerModuleHeader(
            icon = icon,
            title = title,
        ) {
            expandedState.value = !expandedState.value
        }

        val contentSemanticsModifier = Modifier.semantics {
            testTag = "Module content $title"
        }
        if (expandedState.value == ModuleExpandedState.EXPANDED) {
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

sealed class IconType {
    @get: DrawableRes
    abstract val drawableRes: Int

    data class Vector(override val drawableRes: Int) : IconType()
    data class Image(override val drawableRes: Int) : IconType()
}