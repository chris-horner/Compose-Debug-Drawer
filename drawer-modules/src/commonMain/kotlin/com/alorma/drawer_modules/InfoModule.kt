package com.alorma.drawer_modules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alorma.drawer_base.DebugDrawerModule
import com.alorma.drawer_base.DrawerDivider

@Composable
fun InfoModule(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    title: String,
    items: List<Pair<String, String>>
) {
    DebugDrawerModule(
        modifier = modifier,
        icon = icon,
        title = title
    ) {
        Column {
            items.forEachIndexed { index, item ->
                Column {
                    DebugModuleInfoContent(item.first, item.second)
                    if (index < items.size - 1) {
                        DrawerDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun DebugModuleInfoContent(
    key: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.preferredWidth(80.dp)
        ) {
            Providers(
                AmbientContentAlpha provides ContentAlpha.high,
            ) {
                Text(
                    text = key,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
                .padding(8.dp)
        ) {
            Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = value,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}