package com.alorma.drawer_base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.AmbientContentColor
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DrawerModuleHeader(
    icon: @Composable (() -> Unit)? = null,
    title: String,
    expandedState: MutableState<Boolean> = remember { mutableStateOf(false) },
    onClick: () -> Unit = { expandedState.value = !expandedState.value },
) {
    val semanticModifier = Modifier
        .clickable(onClick = onClick)
        .semantics {
            testTag = "Module header $title"
        }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .then(semanticModifier),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.secondary,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                DrawerModuleHeaderIcon(icon)
            } else {
                Spacer(modifier = Modifier.preferredSize(36.dp))
            }
            DrawerModuleHeaderText(
                modifier = Modifier.weight(1f),
                title = title
            )
            DrawerModuleHeaderIcon {
                Icon(
                    imageVector = if (expandedState.value) {
                        Icons.Default.ArrowDropUp
                    } else {
                        Icons.Default.ArrowDropDown
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerModuleHeaderIcon(
    content: @Composable () -> Unit = {}
) {
    Providers(
        AmbientContentColor provides MaterialTheme.colors.onSurface,
    ) {
        Box(
            modifier = Modifier.preferredSize(36.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun DrawerModuleHeaderText(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        color = MaterialTheme.colors.primary,
        modifier = modifier,
        text = title,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun DrawerModuleHeaderExpandedPreview() {
    DrawerModuleHeader(
        icon = { Icon(imageVector = Icons.Default.Adb) },
        title = "Preview",
        expandedState = remember { mutableStateOf(true) },
    ) {

    }
}

@Composable
fun DrawerModuleHeaderCollapsedPreview() {
    DrawerModuleHeader(
        icon = { Icon(imageVector = Icons.Default.Adb) },
        title = "Preview",
        expandedState = remember { mutableStateOf(true) },
    ) {

    }
}

@Composable
fun DrawerModuleHeaderNoIconPreview() {
    DrawerModuleHeader(
        title = "Preview",
        expandedState = remember { mutableStateOf(true) },
    ) {

    }
}