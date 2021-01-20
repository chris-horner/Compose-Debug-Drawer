package com.alorma.drawer_base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DrawerModuleHeader(
    icon: IconType,
    title: String,
    onClick: () -> Unit
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
            DrawerModuleHeaderIcon(icon, 32.dp)
            Spacer(modifier = Modifier.preferredWidth(8.dp))
            DrawerModuleHeaderText(title)
        }
    }
}

@Composable
fun DrawerModuleHeaderIcon(icon: IconType, size: Dp) {
    val modifier = Modifier.preferredSize(size = size)
    when (icon) {
        is IconType.Vector -> Icon(
            tint = MaterialTheme.colors.onSurface,
            modifier = modifier,
            imageVector = vectorResource(
                id = icon.drawableRes
            ),
        )
        is IconType.Image -> Icon(
            tint = MaterialTheme.colors.onSurface,
            modifier = modifier,
            bitmap = imageResource(
                id = icon.drawableRes
            ),
        )
    }
}

@Composable
fun DrawerModuleHeaderText(
    title: String
) {
    val semanticsModifier = Modifier.semantics {
        testTag = "Module header title $title"
    }
    Text(
        color = MaterialTheme.colors.primary,
        modifier = semanticsModifier,
        text = title,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
    )
}
